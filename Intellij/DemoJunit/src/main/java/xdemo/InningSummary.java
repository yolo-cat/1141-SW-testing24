package xdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 局數總結資料類別
 * 用於封裝一局比賽的統計結果，提升可測試性
 */
public class InningSummary {
    private final int runs;      // 得分
    private final int hits;      // 安打數
    private final int outs;      // 出局數
    private final int lob;       // 殘壘數 (Left On Base)
    private final List<Boolean> finalBases;  // 最終壘包狀態

    /**
     * 建構子
     * @param runs 得分數
     * @param hits 安打數
     * @param outs 出局數
     * @param lob 殘壘數
     * @param finalBases 最終壘包狀態 (index 0: 一壘, 1: 二壘, 2: 三壘)
     */
    public InningSummary(int runs, int hits, int outs, int lob, List<Boolean> finalBases) {
        this.runs = runs;
        this.hits = hits;
        this.outs = outs;
        this.lob = lob;
        this.finalBases = new ArrayList<>(finalBases);
    }

    // Getters
    public int getRuns() {
        return runs;
    }

    public int getHits() {
        return hits;
    }

    public int getOuts() {
        return outs;
    }

    public int getLob() {
        return lob;
    }

    public List<Boolean> getFinalBases() {
        return new ArrayList<>(finalBases);
    }

    /**
     * 取得壘包狀態的描述字串
     */
    public String getBaseStateDesc() {
        List<String> runners = new ArrayList<>();
        if (finalBases.get(2)) runners.add("三壘");
        if (finalBases.get(1)) runners.add("二壘");
        if (finalBases.get(0)) runners.add("一壘");

        if (runners.isEmpty()) {
            return "無人在壘";
        } else {
            return String.join("、", runners) + "有人";
        }
    }

    @Override
    public String toString() {
        return String.format("InningSummary{runs=%d, hits=%d, outs=%d, lob=%d, bases=%s}",
                runs, hits, outs, lob, getBaseStateDesc());
    }
}
