package xdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 測試類別：針對 PlayByPlayAnnouncer.getInningSummary() 方法進行單元測試
 * 
 * 測試目標：驗證 getInningSummary() 方法能正確計算一局比賽的統計結果
 * 測試策略：測試正常流程、錯誤處理、邊界情況
 */
class InningSummaryTest {

    // ========== Test Case 1: 正常流程、得分與殘壘 ==========
    
    @Test
    @DisplayName("t01: 正常流程 - 驗證得分、安打、出局、殘壘計算")
    void testGetInningSummary_t01_NormalFlow() {
        // Arrange
        String[] events = {
            "BB",         // 保送 (一壘有人)
            "1B",         // 一壘安打 (一二壘有人)
            "K",          // 三振 (1 out, 一二壘有人)
            "2B 1R",      // 二壘安打，1分得分 (二三壘有人)
            "F8",         // 中外野高飛接殺 (2 out, 二三壘有人)
            "6-3"         // 游擊傳一壘，滾地球出局 (3 out, 二三壘有人)
        };
        
        // Expected values
        int expectedRuns = 1;
        int expectedHits = 2;  // 1B, 2B
        int expectedOuts = 3;  // K, F8, 6-3
        int expectedLOB = 2;   // 二三壘有人
        
        // Act
        InningSummary summary = PlayByPlayAnnouncer.getInningSummary(events);
        
        // Assert
        System.out.println("✓ t01 測試結果: " + summary);
        assertEquals(expectedRuns, summary.getRuns(), "得分數不符");
        assertEquals(expectedHits, summary.getHits(), "安打數不符");
        assertEquals(expectedOuts, summary.getOuts(), "出局數不符");
        assertEquals(expectedLOB, summary.getLob(), "殘壘數不符");
    }

    // ========== Test Case 2: 3 Out 後的速記錯誤 ==========
    
    @Test
    @DisplayName("t02: 3 Out 後的速記錯誤 - 應拋出 ScoringException")
    void testGetInningSummary_t02_ThrowsExceptionAfter3Outs() {
        // Arrange
        String[] events = {
            "K",          // 三振 (1 out)
            "K",          // 三振 (2 out)
            "K",          // 三振 (3 out) -> 局數結束
            "1B"          // 【3 Out 後的無效事件】
        };
        
        // Act & Assert
        PlayByPlayAnnouncer.ScoringException exception = assertThrows(
            PlayByPlayAnnouncer.ScoringException.class,
            () -> PlayByPlayAnnouncer.getInningSummary(events),
            "應該拋出 ScoringException"
        );
        
        System.out.println("✓ t02 測試結果: 成功捕捉例外 - " + exception.getMessage());
        assertTrue(exception.getMessage().contains("3 Out"), 
            "例外訊息應包含 '3 Out'");
        assertTrue(exception.getMessage().contains("1B"), 
            "例外訊息應包含無效事件 '1B'");
    }

    // ========== Test Case 3: 無法解析的速記代碼 ==========
    
    @Test
    @DisplayName("t03: 無法解析的速記代碼 - 應拋出 ScoringException")
    void testGetInningSummary_t03_ThrowsExceptionForInvalidCode() {
        // Arrange
        String[] events = {
            "BB",         // 保送
            "K",          // 三振
            "3B 1R",      // 三壘安打
            "WTF",        // 【無法解析的速記錯誤】
            "HR 3R"       // 全壘打
        };
        
        // Act & Assert
        PlayByPlayAnnouncer.ScoringException exception = assertThrows(
            PlayByPlayAnnouncer.ScoringException.class,
            () -> PlayByPlayAnnouncer.getInningSummary(events),
            "應該拋出 ScoringException"
        );
        
        System.out.println("✓ t03 測試結果: 成功捕捉例外 - " + exception.getMessage());
        assertTrue(exception.getMessage().contains("無法解析"), 
            "例外訊息應包含 '無法解析'");
        assertTrue(exception.getMessage().contains("WTF"), 
            "例外訊息應包含無效代碼 'WTF'");
    }

    // ========== Test Case 4: 保送後安打 ==========
    
    @Test
    @DisplayName("t04: 保送後安打 - 驗證複雜壘包狀態")
    void testGetInningSummary_t04_ComplexBaseRunning() {
        // Arrange
        String[] events = {
            "BB",         // 保送 (一壘有人)
            "BB",         // 保送 (一二壘有人)
            "BB",         // 保送 (滿壘)
            "BB 1R",      // 保送擠回 1 分 (滿壘)
            "2B 2R",      // 二壘安打, 2 分得分 (二三壘有人)
            "K",          // 三振 (1 out, 二三壘有人)
            "BB",         // 保送 (滿壘)
            "K"           // 三振 (2 out, 滿壘)
        };
        
        // Expected values
        int expectedRuns = 3;   // 1R + 2R
        int expectedHits = 1;   // 2B
        int expectedOuts = 2;   // K, K
        int expectedLOB = 3;    // 滿壘
        
        // Act
        InningSummary summary = PlayByPlayAnnouncer.getInningSummary(events);
        
        // Assert
        System.out.println("✓ t04 測試結果: " + summary);
        assertEquals(expectedRuns, summary.getRuns(), "得分數不符");
        assertEquals(expectedHits, summary.getHits(), "安打數不符");
        assertEquals(expectedOuts, summary.getOuts(), "出局數不符");
        assertEquals(expectedLOB, summary.getLob(), "殘壘數不符");
    }

    // ========== 額外測試：全壘打清空壘包 ==========
    
    @Test
    @DisplayName("額外測試: 全壘打清空壘包")
    void testGetInningSummary_HomeRunClearsBases() {
        // Arrange
        String[] events = {
            "BB",         // 保送 (一壘有人)
            "1B",         // 一壘安打 (一二壘有人)
            "HR 3R",      // 全壘打，3 分得分，清空壘包
            "K"           // 三振
        };
        
        // Expected values
        int expectedRuns = 3;
        int expectedHits = 2;   // 1B, HR
        int expectedOuts = 1;   // K
        int expectedLOB = 0;    // 全壘打後壘包清空
        
        // Act
        InningSummary summary = PlayByPlayAnnouncer.getInningSummary(events);
        
        // Assert
        System.out.println("✓ 全壘打測試結果: " + summary);
        assertEquals(expectedRuns, summary.getRuns(), "得分數不符");
        assertEquals(expectedHits, summary.getHits(), "安打數不符");
        assertEquals(expectedOuts, summary.getOuts(), "出局數不符");
        assertEquals(expectedLOB, summary.getLob(), "殘壘數不符");
    }

    // ========== 額外測試：空事件陣列 ==========
    
    @Test
    @DisplayName("額外測試: 空事件陣列")
    void testGetInningSummary_EmptyEvents() {
        // Arrange
        String[] events = {};
        
        // Act
        InningSummary summary = PlayByPlayAnnouncer.getInningSummary(events);
        
        // Assert
        System.out.println("✓ 空事件測試結果: " + summary);
        assertEquals(0, summary.getRuns(), "得分應為 0");
        assertEquals(0, summary.getHits(), "安打應為 0");
        assertEquals(0, summary.getOuts(), "出局應為 0");
        assertEquals(0, summary.getLob(), "殘壘應為 0");
    }

    // ========== 額外測試：只有出局 ==========
    
    @Test
    @DisplayName("額外測試: 只有出局事件")
    void testGetInningSummary_OnlyOuts() {
        // Arrange
        String[] events = {"K", "F8", "6-3"};
        
        // Act
        InningSummary summary = PlayByPlayAnnouncer.getInningSummary(events);
        
        // Assert
        System.out.println("✓ 只有出局測試結果: " + summary);
        assertEquals(0, summary.getRuns(), "得分應為 0");
        assertEquals(0, summary.getHits(), "安打應為 0");
        assertEquals(3, summary.getOuts(), "出局應為 3");
        assertEquals(0, summary.getLob(), "殘壘應為 0");
    }
}
