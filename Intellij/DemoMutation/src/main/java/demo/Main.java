package demo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.muTest(0, 0, 0);
        main.muTest(1, 2, 3);
        main.muTest(1, 1, 1);
    }

    void muTest(int a, int b, int c) {
        ArrayList<String> mutations = new ArrayList<>();
        mutations.add("r2");
        mutations.add("r3");
        mutations.add("r4");

        String r1 = Triangle.getTriangleType(a, b, c);
        String r2 = Triangle02.getTriangleType(a, b, c);
        String r3 = Triangle03.getTriangleType(a, b, c);
        String r4 = Triangle04.getTriangleType(a, b, c);

        if (! r1.equals(r2)) {
            mutations.remove("r2");
        }
        if (! r1.equals(r3)) {
            mutations.remove("r3");
        }
        if (! r1.equals(r4)) {
            mutations.remove("r4");
        }

        System.out.printf("Test data: %d, %d, %d\t", a, b, c);
        System.out.printf("The alive mutations are: %s\n", mutations);
    }
}
