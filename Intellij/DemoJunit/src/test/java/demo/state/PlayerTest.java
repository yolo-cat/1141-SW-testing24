package demo.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p01;
    @BeforeEach
    void setUp() {
        p01 = new Player();
    }

    @Test
    void weakTest() {
        int count = 0;
        do {
            p01.move();
            count ++;
        } while (count < 36);
        assertEquals(28, p01.getEnergy());
        assertTrue(p01.getStatus().equals("虛弱"));
    }
}