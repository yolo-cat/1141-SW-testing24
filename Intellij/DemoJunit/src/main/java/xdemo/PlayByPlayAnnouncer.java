package xdemo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayByPlayAnnouncer {
    private static final String LOG_FILE = "baseball.log";

    // --- 球員名單設定 ---
    private static final String[] DODGERS_BATTERS_LIST = {
            "Shohei Ohtani", "Mookie Betts", "Freddie Freeman",
            "Will Smith", "Max Muncy", "Teo. Hernández",
            "Tommy Edman", "Andy Pages", "Miguel Rojas"
    };

    // 藍鳥隊 (守備方) - 守備位置及球員名單
    private static final Map<Integer, String> BLUE_JAYS_DEFENSE_MAP = new HashMap<>() {{
        put(1, "Max Scherzer");                 // 投手
        put(2, "Alejandro Kirk");               // 捕手
        put(3, "Vladimir Guerrero Jr.");        // 一壘手
        put(4, "Isiah Kiner-Falefa");           // 二壘手
        put(5, "Ernie Clement");                // 三壘手
        put(6, "Bo Bichette");                  // 游擊手
        put(7, "Davis Schneider");              // 左外野手
        put(8, "George Springer");              // 中外野手
        put(9, "Daulton Varsho");               // 右外野手
    }};

    // --- 數據結構與狀態 ---
    private static List<Boolean> bases; // 三個壘包的狀態：index 0: 一壘, 1: 二壘, 2: 三壘
    private static int runs; // 得分
    private static int hits; // 安打
    private static int outs; // 出局數
    private static int batterIndex; // 當前打者索引

    // Test Case 1: 正常流程、得分與殘壘
    private static final String[] t01 = {
            "BB",         // 保送
            "1B",         // 一壘安打
            "K",          // 三振 (1 out)
            "2B 1R",      // 二壘安打，1分得分
            "F8",         // 中外野高飛接殺 (2 out)
            "6-3",        // 游擊傳一壘，滾地球出局 (3 out)
    };

    // Test Case 2: 3 Out 後的速記錯誤
    private static final String[] t02 = {
            "K",          // 三振 (1 out)
            "K",          // 三振 (2 out)
            "K",          // 三振 (3 out) -> 局數結束
            "1B",         // 【3 Out 後的無效事件】
    };

    // Test Case 3: 包含無法解析的速記代碼
    private static final String[] t03 = {
            "BB",         // 保送
            "K",          // 三振
            "3B 1R",      // 三壘安打
            "WTF",        // 【無法解析的速記錯誤】
            "HR 3R",      // 全壘打
    };

    // Test Case 4: 保送後安打
    private static final String[] t04 = {
            "BB",         // 保送
            "BB",         // 保送
            "BB",         // 保送
            "BB 1R",      // 保送
            "2B 2R",      // 二壘安打, 一三壘有人
            "K",
            "BB",         // 保送, 滿壘
            "K",
    };

    // --- 初始狀態 ---
    private static void resetState() {
        runs = 0;
        hits = 0;
        outs = 0;
        batterIndex = 0;
        bases = new ArrayList<>(Arrays.asList(false, false, false)); // 初始化三個壘包為空
    }

    // 獲取事件中的得分數字
    private static int extractRuns(String event) {
        Pattern pattern = Pattern.compile("(\\d+)R"); // 匹配 "nR" 格式
        Matcher matcher = pattern.matcher(event);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0; // 預設為 0 分
    }

    /**
     * 獲取當前壘上狀態的描述字串
     */
    private static String getBaseStateDesc() {
        List<String> runners = new ArrayList<>();
        if (bases.get(2)) runners.add("三壘");
        if (bases.get(1)) runners.add("二壘");
        if (bases.get(0)) runners.add("一壘");

        if (runners.isEmpty()) {
            return "無人在壘";
        } else {
            return String.join("、", runners) + "有人";
        }
    }

    /**
     * 計算殘壘
     */
    private static int calculateLOB() {
        return (int) bases.stream().filter(b -> b).count();
    }


    /**
     * 簡化的跑者推進邏輯: 根據安打類型更新壘上狀態
     * @param hitCount 安打類型：1=一壘安打, 2=二壘安打, 3=三壘安打, 4=全壘打
     *                 0=保送或失誤 (BB/E)
     *                 -1=出局
     */
    private static void updateBases(int hitCount, boolean BB, boolean E) {
        List<Boolean> tempBases = new ArrayList<>(bases);

        if (BB) { // 保送
            if (tempBases.get(0) && tempBases.get(1)) {
                tempBases.set(2, true);
            }
            if (tempBases.get(0)) {
                tempBases.set(1, true);
            }
            tempBases.set(0, true); // 打者上一壘
        } else if (hitCount == 1) { // 一壘安打
            if (tempBases.get(2)  && !tempBases.get(1)) {   // 變化三壘
                tempBases.set(2, false);
            } else if (!tempBases.get(2)  && tempBases.get(1)) {
                tempBases.set(2, true);
            }
            if (tempBases.get(1) && !tempBases.get(0)) {    // 變化二壘
                tempBases.set(1, true);
            } else if (!tempBases.get(1) && tempBases.get(0)) {
                tempBases.set(1, false);
            }
            tempBases.set(0, true); // 打者上一壘
        } else if (hitCount == 2) { // 二壘安打
            if (tempBases.get(2) && !tempBases.get(0)) {    // 變化三壘
                tempBases.set(2, false);  // 清空三壘
            } else if (!tempBases.get(2) && tempBases.get(0)) {
                tempBases.set(2, true);   // 一壘的上三壘
            }
            tempBases.set(1, true);  // 打者上二壘
            tempBases.set(0, false); // 清空一壘
        } else if (hitCount == 3) { // 三壘安打
            tempBases = new ArrayList<>(Arrays.asList(false, false, true)); // 打者上三壘
        } else if (hitCount == 4) { // 全壘打
            tempBases = new ArrayList<>(Arrays.asList(false, false, false));
        }
        bases = tempBases;
    }



    /**
     * 【學生作答區: 錯誤日誌紀錄】
     */
    private static void logError(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pw.printf("[%s] ERROR: %s%n", timestamp, message);
            
        } catch (IOException e) {
            System.err.println("無法寫入 log 檔案: " + e.getMessage());
        }
    }
    // --- 核心播報邏輯 ---
    public static void announceInning(String[] events) {
        resetState();

        System.out.println("==========================================");
        System.out.println("        MLB 即時播報：道奇 vs 藍鳥          ");
        System.out.println("==========================================");

        //  【學生作答區: 例外處理】
        for (String event : events) {
            // 檢查 3 Out 之後的速記錯誤
            if (outs == 3) {
                String errorMessage = String.format("速記錯誤：局數已達 3 Out，但仍有事件 '%s' 發生。", event);
                logError(errorMessage);
                throw new ScoringException(errorMessage);
//                Mid-E2：Q2a
            }

            String currentBatter = DODGERS_BATTERS_LIST[batterIndex % DODGERS_BATTERS_LIST.length];

            System.out.printf("\n--- 第 %d 棒打者: %s ---\n",
                    (batterIndex % DODGERS_BATTERS_LIST.length) + 1, currentBatter);
            System.out.printf("   (當前: %d 出局, 壘上狀態: %s)\n", outs, getBaseStateDesc());

            int eventRuns = extractRuns(event); // 提取事件中的得分
            runs += eventRuns;

            String outcome = getAnnounceOutcome(event, eventRuns, currentBatter);

            int oldOuts = outs;
            int hitCount = -1; // -1: 出局, 0: BB/E, 1: 1B, 2: 2B, 3: 3B, 4: HR
            boolean BB = false;  // bad ball
            boolean E = false;   // error

            boolean advBase = event.startsWith("BB") || event.startsWith("E");
            advBase = advBase || event.startsWith("1B") || event.startsWith("2B");
            advBase = advBase || event.startsWith("3B") || event.startsWith("HR");

            if (advBase) {
                if (event.startsWith("BB")) {
                    hitCount = 0;
                    BB = true;
                } else if (event.startsWith("E")) {
                    hitCount = 0;
                    E = true;
                } else if (event.startsWith("1B")) {
                    hits++;
                    hitCount = 1;
                } else if (event.startsWith("2B")) {
                    hits++;
                    hitCount = 2;
                } else if (event.startsWith("3B")) {
                    hits++;
                    hitCount = 3;
                } else if (event.startsWith("HR")) {
                    hits++;
                    hitCount = 4;
                }
                batterIndex++;
                updateBases(hitCount, BB, E);
            }
            else {
                if (event.matches("^K.*")) {
                    outs++;
                    batterIndex++;
                } else if (event.matches("^F[0-9].*") || event.matches("^[0-9]-[0-9].*")) {
                    outs++;
                    batterIndex++;
                } else {
                    String errorMessage = String.format("無法解析的速記代碼: '%s'，來自打者: %s", event, currentBatter);
                    logError(errorMessage);
                    throw new ScoringException(errorMessage);
//                    Mid-E2：Q2b
                }
            }

            System.out.println(outcome);

            if (eventRuns > 0) {
                System.out.printf(" >> ！！%s 為球隊帶回分數！！本次打席總共獲得 %d 分。累計得分：R=%d, H=%d, O=%d\n",
                        currentBatter.split(" ")[1], eventRuns, runs, hits, outs);
            }
        }

        System.out.println("\n==========================================");
        System.out.println("               本局總結報告               ");
        System.out.println("==========================================");
        System.out.printf("最終得分 (R)：%d\n", runs);
        System.out.printf("總安打數 (H)：%d\n", hits);
        System.out.printf("最終出局數 (O)：%d\n", outs);
        System.out.printf("殘壘數 (LOB)：%d (%s)\n", calculateLOB(), getBaseStateDesc());
        System.out.println("==========================================");
    }


        // 根據速記代碼產生中文播報內容 (含姓名)
    private static String getAnnounceOutcome(String event, int eventRuns, String batterName) {
        String batter = batterName;
//       Mid-E2: Q1b:String batter = batterName.split("0")[0];

        if (event.equals("BB")) {
            return String.format("打者 %s 獲得四壞球保送 (BB)，站上一壘。", batter);
        } else if (event.equals("K")) {
            return String.format("投手壓制了 %s！打者遭到三振出局 (K)！", batter);
        } else if (event.startsWith("F")) {
            int pos = Integer.parseInt(event.substring(1).replaceAll("[^0-9]", ""));
            String fielder = BLUE_JAYS_DEFENSE_MAP.getOrDefault(pos, "外野手");
            String fielderName = fielder.split(" ")[1];
//            Mid-E2:Q1a: String fielderName = fielder.split("-")[1];

            return String.format("%s 高飛球，被 %s (%s) 穩穩接殺出局 (F%d)！",
                    batter, fielderName, fielder.split(" ")[0], pos);

        } else if (event.matches("^[0-9]-[0-9].*")) {
            String[] positions = event.split("-");
            int firstPos = Integer.parseInt(positions[0].replaceAll("[^0-9]", ""));
            int lastPos = Integer.parseInt(positions[1].split(" ")[0].replaceAll("[^0-9]", ""));

            String firstFielder = BLUE_JAYS_DEFENSE_MAP.getOrDefault(firstPos, "守備員");
            String lastFielder = BLUE_JAYS_DEFENSE_MAP.getOrDefault(lastPos, "守備員");
            String firstFName = firstFielder.split(" ")[0];
            String lastFName = lastFielder.split(" ")[0];

            return String.format("%s 滾地球！%s 迅速接球，傳給 %s 完成刺殺出局 (%s)！",
                    batter, firstFName, lastFName, event.split(" ")[0]);
        } else if (event.startsWith("1B")) {
            return String.format("安打！%s 敲出一壘穿越安打 (1B)！", batter);
        } else if (event.startsWith("2B")) {
            return String.format("長打出現！%s 擊出深遠二壘安打 (2B)！", batter);
        } else if (event.startsWith("3B")) {
            return String.format("驚人速度！%s 衝出三壘安打 (3B)！", batter);
        } else if (event.startsWith("HR")) {
            return String.format("全、壘、打 (HR)！！來自 %s 的驚天一擊！", batter);
        } else {
            return "錯誤";
        }
    }

  public static class ScoringException extends RuntimeException {
    public ScoringException(String message) {
      super(message);
    }
  }

public static void main(String[] args) {
    try {
        System.out.println("t01: 正常流程與殘壘 ---");
        announceInning(t01);
    } catch (ScoringException e) {
        System.err.println("發生錯誤：" + e.getMessage());
    }

    try {
        System.out.println("\n\nt02: 3 Out 後的速記錯誤  ---");
        announceInning(t02);
    } catch (ScoringException e) {
        System.err.println("發生錯誤：" + e.getMessage());
    }

    try {
        System.out.println("\n\nt03: 無法解析的速記代碼錯誤 ---");
        announceInning(t03);
    } catch (ScoringException e) {
        System.err.println("發生錯誤：" + e.getMessage());
    }

    try {
        System.out.println("\n\nt04 ---");
        announceInning(t04);
    } catch (ScoringException e) {
        System.err.println("發生錯誤：" + e.getMessage());
    }
}
}

