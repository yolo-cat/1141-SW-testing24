package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClubTest {


    @Test
    void choose() {
        Club c = new Club();
        String r = c.choose(40, "男", 20, 172);
        assertEquals("", r);
    }
}