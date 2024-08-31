package fcu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testApp() {
        assertTrue(true);
    }
    @Test
    public void testAdd() {
        App app = new App();
        int real = app.add(1,1);
        int expected = 2;
        assertEquals(expected, real);
    

    }
}
