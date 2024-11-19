package demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringUtilityTest {

    @org.junit.jupiter.api.Test
    void isPalindrome() {
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(StringUtility.isPalindrome("noon"));
    }

}