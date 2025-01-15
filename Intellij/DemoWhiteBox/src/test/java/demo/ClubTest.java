package demo;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {


    @org.junit.jupiter.api.Test
    void choose() {
        Club c = new Club();
        String r = c.choose(40, "男", 20, 172);
        assertEquals("排球社", r);
    }
}