package xdemo;

/**
 * Use break/watch to debug and watch program execution
 */
public class GCD_Lab {

    public static void main(String[] args) {
        int r;
        GCD_Lab g = new GCD_Lab();
        r = g.gcd(24,18);
        System.out.println(r);

        r = g.gcd2(24, 18);
        System.out.println(r);
    }

    public int gcd(int m, int n) {
        int r = 0;
        while (n != 0) {
            r = m % n;
            m = n;
            n = r;
        }
        return m;
    }

    public int gcd2(int m, int n) {
        if (n == 0)
            return m;
        else {
            return gcd2(n, m % n);
        }
//        return n;
    }
}
