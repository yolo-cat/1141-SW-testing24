package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


record CalculatorTest() {

    @Test
    @DisplayName("Calculator ok 的啦")
    void add() {
        Calculator cal = new Calculator();
        int expected = 2;
        int real = cal.add(1,1);
        assertEquals(expected, real);
    }
}