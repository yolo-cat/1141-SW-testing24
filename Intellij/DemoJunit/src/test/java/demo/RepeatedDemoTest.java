package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Logger;

import org.junit.jupiter.api.*;

class RepeatedDemoTest {

    private static final Logger logger = Logger.getLogger(RepeatedDemoTest.class.getName());
    static final String LONG_DISPLAY_NAME = "{displayName} :: repetition {currentRepetition} of {totalRepetitions}";

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        logger.info(String.format("About to execute repetition %d of %d for %s", //
                currentRepetition, totalRepetitions, methodName));
    }

    // 當環境是隨機時，我們可以進行多次測試，確保程式的正確
    @RepeatedTest(10)
    void repeatedTest() {

    }

    // 可以透過 RepetitionInfo 來取得重複測試的相關資訊
    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    // 當錯誤超過 2 次，就會取消之後的測試
    @RepeatedTest(value = 8, failureThreshold = 2)
    void repeatedTestWithFailureThreshold(RepetitionInfo repetitionInfo) {
        // Simulate unexpected failure every second repetition
        if (repetitionInfo.getCurrentRepetition() % 2 == 0) {
            fail("Boom!");
        }
    }

    // name 可以做一些客製化
    @RepeatedTest(value = 3, name="{displayName} ({currentRepetition}/{totalRepetitions})")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
    }

    // 同上，name 可以做一些客製化
    @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
    void repeatedTestInGerman() {
        // ...
    }

    // 同上
    @RepeatedTest(value = 1, name = RepeatedDemoTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
    }


}