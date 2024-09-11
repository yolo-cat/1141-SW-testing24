public class GCD {

    public static void main(String[] args) {

        GCD g = new GCD();
        // g.gcd(24,18);
        System.out.println(g.gcd2(24, 18));

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
            gcd2(n, m % n);
        }
        return m;
    }
}