package fcu;

import  org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    Triangle triangle = new Triangle();

    @Test
    void isTriangle() {
        assertEquals(true, triangle.isTriangle(10, 10, 10));
    }

    @Test
    void isTriangle2() {
        assertEquals(true, triangle.isTriangle(1, 10, 10));
    }

}