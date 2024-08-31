package fcu;

public class Triangle {

    public boolean isTriangle(int a, int b, int c) {
        if (a+b<=c) {
            return false;
        }
        if (b+c<=a) {
            return false;
        }
        if (c+a<=b) {
            return false;
        }
        return true;
    }
}
