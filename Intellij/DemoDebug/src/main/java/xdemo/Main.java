package xdemo;

public class Main {
  public static void main(String[] args) {
    int x = -1;
    int y = square(x);
    assert y >= 0 : "y must be non-negative";
    System.out.println(y);
  }

  public static int square(int x) {
    return x * x;
  }
}
