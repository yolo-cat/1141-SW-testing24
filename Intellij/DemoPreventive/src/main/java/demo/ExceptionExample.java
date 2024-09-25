package demo;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 這裡會引發 ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("捕捉到例外: " + e.getMessage());
        } finally {
            // usually used for closing files
            System.out.println("無論如何都會執行");
        }
        System.out.println("之後呢？");

        (new ExceptionExample()).divideByZero();;
    }

    public void divideByZero() {
        // This will cause a unchecked exception, and terminate the program
        double zero = 0.0;
        int x = 10 / 0;
        System.out.println("Hello, I am divided by zero");
    }
}
