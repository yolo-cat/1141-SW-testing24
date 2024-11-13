package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void not_valid_triangle() {
        assertAll("not validate",
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(-1, 2, 3)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, -2, 3)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, 2, -3))
                )
        ;
    }
}