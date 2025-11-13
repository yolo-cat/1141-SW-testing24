package xdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class PlayByPlayAnnouncerTest {

    private int callExtractRuns(String event) throws Exception {
        Method method = PlayByPlayAnnouncer.class.getDeclaredMethod("extractRuns", String.class);
        method.setAccessible(true);
        return (int) method.invoke(null, event);
    }
    
    // ========== 基本需求測試 ==========

    /**
     * 【需求測試 1】extractRuns("2B 1R") ⇒ 1
     * 測試目的：驗證能正確提取二壘安打帶 1 分的情況
     * 
     * 可能的錯誤實作：
     * - 錯誤 1: 沒有正確解析 "1R" 格式
     * - 錯誤 2: 回傳了錯誤的數字（如 2）
     * - 錯誤 3: 忽略了空格後的得分資訊
     */
    @Test
    @DisplayName("extractRuns(\"2B 1R\") ⇒ 1")
    void testExtractRuns_2B1R_ShouldReturn1() throws Exception {
        // Arrange
        String event = "2B 1R";
        int expected = 1;
        
        // Act
        int actual = callExtractRuns(event);
        
        // Assert
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(expected, actual, 
            String.format("❌ 測試失敗！extractRuns(\"%s\") ⇒ 預期 %d，實際 %d", 
                event, expected, actual));
    }

    /**
     * 【需求測試 2】extractRuns("K") ⇒ 0
     * 測試目的：驗證三振事件沒有得分
     * 
     * 可能的錯誤實作：
     * - 錯誤 1: 沒有處理無得分的情況，回傳 null 或拋出例外
     * - 錯誤 2: 錯誤地從 "K" 中提取出數字
     * - 錯誤 3: 預設值不是 0
     */
    @Test
    @DisplayName("extractRuns(\"K\") ⇒ 0")
    void testExtractRuns_K_ShouldReturn0() throws Exception {
        // Arrange
        String event = "K";
        int expected = 0;
        
        // Act
        int actual = callExtractRuns(event);
        
        // Assert
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(expected, actual, 
            String.format("❌ 測試失敗！extractRuns(\"%s\") ⇒ 預期 %d，實際 %d", 
                event, expected, actual));
    }

    // ========== 擴展測試案例：測試其他情況 ==========
    
    /**
     * 測試案例 3: "HR 3R" ⇒ 3
     * 測試目的：驗證能正確提取全壘打的多分得分
     */
    @Test
    @DisplayName("extractRuns(\"HR 3R\") ⇒ 3")
    void testExtractRuns_HR3R_ShouldReturn3() throws Exception {
        String event = "HR 3R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(3, actual);
    }

    /**
     * 測試案例 4: "BB" ⇒ 0
     * 測試目的：驗證保送沒有得分記錄
     */
    @Test
    @DisplayName("extractRuns(\"BB\") ⇒ 0")
    void testExtractRuns_BB_ShouldReturn0() throws Exception {
        String event = "BB";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }

    /**
     * 測試案例 5: "BB 1R" ⇒ 1
     * 測試目的：驗證保送擠回 1 分的情況
     */
    @Test
    @DisplayName("extractRuns(\"BB 1R\") ⇒ 1")
    void testExtractRuns_BB1R_ShouldReturn1() throws Exception {
        String event = "BB 1R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(1, actual);
    }

    /**
     * 測試案例 6: "3B 1R" ⇒ 1
     * 測試目的：驗證三壘安打帶 1 分的情況
     */
    @Test
    @DisplayName("extractRuns(\"3B 1R\") ⇒ 1")
    void testExtractRuns_3B1R_ShouldReturn1() throws Exception {
        String event = "3B 1R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(1, actual);
    }

    /**
     * 測試案例 7: "1B" ⇒ 0
     * 測試目的：驗證一壘安打沒有得分記錄
     */
    @Test
    @DisplayName("extractRuns(\"1B\") ⇒ 0")
    void testExtractRuns_1B_ShouldReturn0() throws Exception {
        String event = "1B";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }

    /**
     * 測試案例 8: "2B 2R" ⇒ 2
     * 測試目的：驗證二壘安打帶 2 分的情況
     */
    @Test
    @DisplayName("extractRuns(\"2B 2R\") ⇒ 2")
    void testExtractRuns_2B2R_ShouldReturn2() throws Exception {
        String event = "2B 2R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(2, actual);
    }

    /**
     * 測試案例 9: "F8" ⇒ 0
     * 測試目的：驗證高飛接殺沒有得分
     */
    @Test
    @DisplayName("extractRuns(\"F8\") ⇒ 0")
    void testExtractRuns_F8_ShouldReturn0() throws Exception {
        String event = "F8";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }

    /**
     * 測試案例 10: "6-3" ⇒ 0
     * 測試目的：驗證滾地球出局沒有得分
     */
    @Test
    @DisplayName("extractRuns(\"6-3\") ⇒ 0")
    void testExtractRuns_63_ShouldReturn0() throws Exception {
        String event = "6-3";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }
    
    // ========== 邊界測試：找出潛在錯誤 ==========
    
    /**
     * 邊界測試 1: "HR 4R" ⇒ 4 (滿貫全壘打)
     * 測試目的：驗證能處理滿貫全壘打（4分）
     * 可能錯誤：只能處理 1-3 分的情況
     */
    @Test
    @DisplayName("extractRuns(\"HR 4R\") ⇒ 4")
    void testExtractRuns_HR4R_ShouldReturn4() throws Exception {
        String event = "HR 4R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(4, actual);
    }
    
    /**
     * 邊界測試 2: "" ⇒ 0 (空字串)
     * 測試目的：驗證能處理空輸入
     * 可能錯誤：拋出 NullPointerException 或其他例外
     */
    @Test
    @DisplayName("extractRuns(\"\") ⇒ 0")
    void testExtractRuns_EmptyString_ShouldReturn0() throws Exception {
        String event = "";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }
    
    /**
     * 邊界測試 3: "2B 10R" ⇒ 10 (兩位數得分)
     * 測試目的：驗證能處理兩位數的得分
     * 可能錯誤：只能處理個位數，或錯誤地只取第一位數字
     */
    @Test
    @DisplayName("extractRuns(\"2B 10R\") ⇒ 10")
    void testExtractRuns_2B10R_ShouldReturn10() throws Exception {
        String event = "2B 10R";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(10, actual);
    }
    
    /**
     * 邊界測試 4: "WTF" ⇒ 0 (無效代碼)
     * 測試目的：驗證能處理無效的速記代碼
     * 可能錯誤：拋出例外而不是回傳 0
     */
    @Test
    @DisplayName("extractRuns(\"WTF\") ⇒ 0")
    void testExtractRuns_InvalidCode_ShouldReturn0() throws Exception {
        String event = "WTF";
        int actual = callExtractRuns(event);
        System.out.println("✓ extractRuns(\"" + event + "\") ⇒ " + actual);
        assertEquals(0, actual);
    }
}
