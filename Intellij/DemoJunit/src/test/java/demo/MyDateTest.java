package demo;

import xdemo.MyDate;

import static org.junit.jupiter.api.Assertions.*;

class MyDateTest {

    @org.junit.jupiter.api.Test
    void to_m() {
//        MyDate d = new MyDate(2024, "1", 12);
        assertEquals(1, MyDate.to_m("1"));
    }
}