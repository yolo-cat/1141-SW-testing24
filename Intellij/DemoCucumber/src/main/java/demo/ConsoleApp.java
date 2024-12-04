package demo;

public class ConsoleApp {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        int result = app.add(10, 20);
        System.out.println("Result: " + result);
    }
}