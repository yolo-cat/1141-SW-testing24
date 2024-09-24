## 例外處理

> 把「例外」視為「常態」是專業工程師必備的態度。

你不能掌控的事都可能出錯，發生例外。要寫一個穩健（robust）的系統，必須非常的細心，考慮到各種可能的例外情況，各種可能出錯的環境變數，並且做出應對。


Java 的例外處理機制（Exception Handling）是一種用來管理程式在執行期間所發生異常狀況的機制，使得程式能夠在面對錯誤時繼續執行或優雅地中止。Java 使用 `try-catch-finally` 這樣的結構來捕捉並處理例外情況。

以下是 Java 例外處理的關鍵概念與操作方式：

### 1. **例外類別層次結構**
Java 中的例外都是基於 `Throwable` 類別。它分為兩大類：
- **Checked Exception**（受檢例外）：這些是程式在編譯時就必須處理的例外，例如 `IOException`、`SQLException` 等。這類例外要求必須使用 `try-catch` 或在方法上宣告 `throws`。
- **Unchecked Exception**（未檢例外）：這些是繼承自 `RuntimeException` 的例外，如 `NullPointerException`、`ArrayIndexOutOfBoundsException` 等。這些例外通常是程式邏輯錯誤，編譯時不強制要求處理。

### 2. **例外處理的語法**
Java 例外處理主要使用以下三個關鍵字：

- `try`：包含可能會拋出例外的代碼塊。
- `catch`：用來捕捉並處理特定的例外。
- `finally`：無論是否發生例外，`finally` 區塊內的程式碼都會被執行（通常用來釋放資源）。

範例：
```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 這裡會引發 ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("捕捉到例外: " + e.getMessage());
        } finally {
            System.out.println("無論如何都會執行");
        }
    }
}
```
輸出：
```
捕捉到例外: / by zero
無論如何都會執行
```

### 3. **throws 關鍵字**
當一個方法內可能會拋出受檢例外，但該方法不打算處理時，可以使用 `throws` 關鍵字將例外拋給上層呼叫者處理。例如：
```java
public void readFile() throws IOException {
    // 這裡會拋出 IOException，並告訴呼叫者需要處理
}
```

### 4. **throw 關鍵字**
`throw` 關鍵字用來手動拋出例外。例如：
```java
public void checkAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("年齡必須大於18");
    }
}
```

### 5. **多重 catch 區塊**
你可以在一個 `try` 區塊後使用多個 `catch` 區塊來處理不同類型的例外：
```java
try {
    // 可能會拋出多種例外的代碼
} catch (NullPointerException e) {
    System.out.println("NullPointerException 被捕捉到");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("ArrayIndexOutOfBoundsException 被捕捉到");
} catch (Exception e) {
    System.out.println("其他例外: " + e.getMessage());
}
```

### 6. **try-with-resources**
這是一個自動管理資源的結構，適用於實現了 `AutoCloseable` 介面的資源（如文件流）。當使用這個結構時，資源會在結束後自動關閉，無需在 `finally` 中手動釋放資源。
```java
try (FileReader reader = new FileReader("test.txt")) {
    // 讀取文件
} catch (IOException e) {
    System.out.println("捕捉到 IO 例外");
}
```

Java 的例外處理機制提供了靈活性與可讀性，使得程式能夠在面對不預期情況時正確處理並維持穩定運行。


## 捕捉或宣告原則
捕捉或宣告原則 Catch or Declare Rule (CDR)：
> 對於例外你只有兩個選擇：一是處理它，二是宣告這個例外讓呼叫者處理。

> 寫一個模組程式和做一件事一樣，一方面你要把事情做好，一方面你要知道如何界定工作範圍 -- 對於你不能處理的事，你得上承給他人處理。



### People example

```java
class People {
    String name;
    float height, weight, bmi;
    int birthYear;

    public People(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    // People 宣告可能會拋出例外
    public People(String name, float height, float weight, int birthYear) throws Exception {
        this.birthYear = birthYear;
        this.name = name;
        setHW(height, weight);
    }

    // setHW 也會拋出例外
    public void setHW(float height, float weight) throws Exception {
        if (height > 2.2) {
            throw new Exception("invalid height");
        }
        this.height = height;
        this.weight = weight;
        this.bmi = weight / (height * height);
    }

    float getBMI() {
        return bmi;
    }
}

public class Main {
    public static void main(String[] args) {
        People nick = null;
        try {
            nick = new People("name", 1.7f, 60, 1990);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bmi = nick.getBMI();
        System.out.println(bmi);

        try {
            nick.setHW(180, 90);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

## 自訂例外

自定義例外類別在 Java 中非常簡單，你可以透過繼承 `Exception` 或 `RuntimeException` 類別來創建屬於你自己的例外類別。`TriangleException` 可以用來表示三角形相關的錯誤，例如當邊長不符合三角形不等式定理時拋出此例外。


```java
public class TriangleException extends Exception {
    private double side1;
    private double side2;
    private double side3;

    public TriangleException(String message, double a, double b, double c) {
        super(message);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return super.getMessage() + ": (" + a + ", " + b + ", " + c + ")";
    }
}
```

## Lab

### Lab01: People
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/People.java)
* 那些不合理的生日

### Lab02: Triangle
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/Triangle.java)
* 當長度是負的！

### Lab03: LowestCommonAncestor
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/LowestCommonAncestor.java)
* 並沒有在樹上

### Lab04: Medal
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/MedalReader.java)
* 除了金銀銅還有其他的？
* 不是數字的金銀銅？