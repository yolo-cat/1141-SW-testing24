package fcu;

import static org.junit.jupiter.api.Assertions.*;

import  org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Disabled;
import  org.junit.jupiter.api.DisplayName;
import  org.junit.jupiter.api.Test;
import  org.junit.jupiter.params.ParameterizedTest;
import  org.junit.jupiter.params.provider.CsvSource;
import  org.junit.jupiter.params.provider.ValueSource;

class SwimmingPoolTest {
    SwimmingPool p;

    @BeforeEach
    void setUp() throws Exception {
        p = new SwimmingPool();
    }

    @DisplayName("Old man test")
    @Test
    void testOld() {
        assertEquals(50, p.price("Monday", false, 70));
        assertEquals(50, p.price("Tuesday", true, 75));
        assertEquals(50, p.price("Wednesday", false, 80));
    }

    @DisplayName("Old man- parameterize test")
    @ParameterizedTest
    @ValueSource(ints = { 70, 71, 73, 100})
    void testOldMan(int age) {
        assertEquals(50, p.price("Monday", false, age));
        assertEquals(50, p.price("Wednesday", false, age));
    }

    @DisplayName("Old man- CSV test")
    @ParameterizedTest
    @CsvSource({
            "Monday,         70",
            "Sunday,         70",
            "Sunday,         90",
    })
    void testOldMan2(String day, int age) {
        assertEquals(50, p.price(day, false, age));
    }

    @DisplayName("Holiday test- parameter test")
    @ParameterizedTest
    @ValueSource(strings = {"Sunday", "Saturday"})
    void testHoliday(String day) {
        assertAll("member",
                () -> {assertEquals(120, p.price(day, true, 20));},
                () -> {assertEquals(50, p.price(day, true, 70));}
        );
        assertAll("not member",
                () -> {assertEquals(150, p.price(day, false, 20));},
                () -> {assertEquals(50, p.price(day, false, 70));}
        );
    }

    @DisplayName("Working day- assertAll test")
    @Test
    void testWorkingDay() {
        assertAll("member",
                () -> {assertEquals(70, p.price("Monday", true, 50));},
                () -> {assertEquals(70, p.price("Tuesday", true, 20));},
                () -> {assertEquals(50, p.price("Tuesday", true, 70));}
        );
        assertAll("not member",
                () -> {assertEquals(100, p.price("Monday", false, 50));},
                () -> {assertEquals(100, p.price("Tuesday", false, 20));},
                () -> {assertEquals(50, p.price("Tuesday", false, 70));}
        );
    }
}

