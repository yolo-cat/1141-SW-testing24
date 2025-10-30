package demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

record CalculatorTest() {

//針對計算機程式，初始化測試環境，並在每個案例中回收避免污染環境


  @BeforeEach
  void setUp() {
    System.out.println("測試開始");
  }

  @AfterEach
  void tearDown() {
    System.out.println("測試結束");
  }


  @org.junit.jupiter.api.Test
  @DisplayName("Calculator ok 的啦")
  void test_plus() {
    Calculator cal = new Calculator();
    int expected = 2;
    int real = cal.add(1, 1);

//  方法1: 單一測試案例
//    assertEquals(expected, real);
//    assertEquals(0, cal.add(1, -1));
//    assertEquals(-2, cal.add(-1, -1));

//  方法2: 使用 assertall
    assertAll("test assert all on cal",
        () -> System.out.println("執行加法測試"),
        () -> assertEquals(2, cal.add(1, 1)),
        () -> assertEquals(0, cal.add(1, -1))
    );
  }

  //  減法
  @org.junit.jupiter.api.Test
  @DisplayName("Calculator ok 的啦")
  void test_subtract() {
    Calculator cal = new Calculator();
    int expected = 0;
    int real = cal.subtract(1, 1);
    assertEquals(expected, real);
  }

  //  乘法
  @org.junit.jupiter.api.Test
  @DisplayName("Calculator ok 的啦")
  void test_multiply() {
    Calculator cal = new Calculator();
    int expected = 1;
    int real = cal.multiply(1, 1);
    assertEquals(expected, real);
  }

  //  除法
  @org.junit.jupiter.api.Test
  @DisplayName("Calculator div OK 的啦")
  void test_div() throws Exception {
    Calculator cal = new Calculator();
    int expected = 2;
    double real = cal.divide(4, 2);
    assertEquals(expected, real);
  }

//  除法例外
  @org.junit.jupiter.api.Test
  @DisplayName("Calculator div by zero")
  void test_div_by_zero() {
    Calculator cal = new Calculator();
    Exception exception = assertThrows(Exception.class, () -> {
      cal.divide(4, 0);
    });
    String expectedMessage = "Division by zero";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }


//  使用 assertall


//  使用 BeforeEach 來初始化測試環境

//  使用 AfterEach 來回收測試環境

//
//
//    @Test
//    @DisplayName("Calculator ok 的啦")
//    void test_plus() {
//        Calculator cal = new Calculator();
//        int expected = 2;
//        int real = cal.add(1,1);
//        assertEquals(expected, real);
//        assertEquals(0, cal.add(1, -1));
//        assertEquals(-2, cal.add(-1, -1));
//
////        使用 assertall
//        assertAll("test assert all on cal",
//            ()-> System.out.println("執行加法測試"),
//            ()-> assertEquals(2, cal.add(1,1)),
//            ()-> assertEquals(0, cal.add(1, -1))
////            設計一個出錯的測試案例
////            ()-> assertEquals(-1, cal.add(-1, -1))
//        );
//    }
//
//    @BeforeEach
//    void add_pp() {
//        Calculator cal = new Calculator();
//        System.out.println("執行加法測試");
//        assertEquals(2, cal.add(1,1));
//    }
//
//    @Test
//    void test_subtract() {
//        Calculator cal = new Calculator();
//        int expected = 0;
//        int real = cal.subtract(1,1);
//        assertEquals(expected, real);
//    }
//
//    @Test
//    void test_multiply() {
//        Calculator cal = new Calculator();
//        int expected = 1;
//        int real = cal.multiply(1,1);
//        assertEquals(expected, real);
//    }
//
//    @Test
//    void test_assertAll() {
//        Calculator cal = new Calculator();
//        assertAll("test assert all on cal",
//                ()-> assertEquals(2, cal.add(1,1)),
//                ()-> assertEquals(0, cal.add(1, -1))
//        );
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Calculator div OK 的啦")
//    void test_div() throws Exception {
//        Calculator cal = new Calculator();
//        int expected = 2;
//        double real = cal.divide(4,2);
//        assertEquals(expected, real);
//
//
//    }

}