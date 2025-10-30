package demo;

public class Calculator {

  public int add(int x, int y) {
    return x + y;
  }

  public int subtract(int a, int b) {
    return a - b;
  }

  public int multiply(int a, int b) {
    return a * b;
  }

  public double divide(int a, int b) throws Exception {
    if (b == 0) {
      throw new Exception("Division by zero is not allowed.");
    }
    return (double) a / b;
  }

}
