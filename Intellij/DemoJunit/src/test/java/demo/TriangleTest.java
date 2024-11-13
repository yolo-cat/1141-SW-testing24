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

//    Equilateral
@Test
void testEquilateral() {
    assertAll("Equilateral",
            ()-> assertEquals("Equilateral", Triangle.getTriangleType(3, 3, 3)),
            ()-> assertEquals("Equilateral", Triangle.getTriangleType(2, 2, 2))
    )
    ;
}

//Isosceles
@Test
void testIsosceles() {
    assertAll("Isosceles",
            ()-> assertEquals("Isosceles", Triangle.getTriangleType(3, 3, 2)),
            ()-> assertEquals("Isosceles", Triangle.getTriangleType(2, 3, 2))
    )
    ;
}

}