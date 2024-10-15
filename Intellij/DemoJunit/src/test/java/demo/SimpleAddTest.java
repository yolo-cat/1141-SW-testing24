package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleAddTest {

    @Test
    void intAdd() {
        SimpleAdd adder = new SimpleAdd();
        int expectedResult = 2;
        assertEquals(expectedResult, adder.intAdd(1,1));
    }
}