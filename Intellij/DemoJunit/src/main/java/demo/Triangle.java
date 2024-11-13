package demo;

import java.util.Scanner;

public class Triangle {
    public static String getTriangleType(double a, double b, double c) {
            if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
                return "Not a valid triangle";
            }

            // Check for equilateral triangle
            if (a == b && b == c && a == c) {
                return "Equilateral";
            }

            // Check for isosceles triangle
            if (a == b || b == c || a == c) {
                return "Isosceles";
            }

            // Otherwise, it's a scalene triangle
            return "Scalene"; // 不等邊三角形
        }

}
