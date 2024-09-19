## 例外處理

> 把「例外」視為「常態」是專業工程師必備的態度。

你不能掌控的事都可能出錯，發生例外。要寫一個穩健（robust）的系統，必須非常的細心，考慮到各種可能的例外情況，各種可能出錯的環境變數，並且做出應對。

Java 中例外的種類：
  
- 需查核例外 `checked exception`: 你必須處理它（exception handle）或宣告它（讓別人來處理）。 如果你都沒有做，compiler 不會通過，直到你寫出你的*處理碼*。在 Java 中，Exception 下的類別，除了 RuntimeException 以外都是需查核例外。
- 非查核例外 `unchecked exception`: 通常是程式邏輯的錯誤，例如 `NullPointerException`, `ArrayIndexOutOfBoundsException` -- 當發現此例外，你應該修正你的程式，而不是做例外處理，所以這一類的錯誤並不強制去處理。在 Java 中，`RuntimeException` 下的類別都屬於 非查核例外。

### 捕捉或宣告
捕捉或宣告原則 Catch or Declare Rule (CDR)：
> 對於例外你只有兩個選擇：一是處理它，二是宣告這個例外讓呼叫者處理。

> 寫一個模組程式和做一件事一樣，一方面你要把事情做好，一方面你要知道如何界定工作範圍 -- 對於你不能處理的事，你得上承給他人處理。

### 捕捉後處理例外

這個程式中我們必須讀取 grade.txt，這是程式邏輯無法控制的事（無法確定執行環境會不會有此檔案），所以有可能產生例外。`FileNotFoundException` 是 `Exception` 的子類別，屬於 需查核例外，我們的處理方式就是呈現訊息，並且跳離系統。

```java
// use Exception
static void openFile() {
   try {
      FileInputStream f = new FileInputStream("grade.txt");
   } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
      e.printStackTrace();
    }
}
```

### 拋出例外

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

### 自訂例外

```java
// Define your exception
class WrongGradeException extends Exception {
    public WrongGradeException(String title) {
        super(title);
    }

    public WrongGradeException() {
        super("The grade is wrong, maybe greater than 100 or less than 0");
    }
}

public class Grade {
    /*
     * This program will demo how to define and use your exception
     */
    static double getGradeAverage(int g[]) throws WrongGradeException {
        int sum = 0;
        for (int i = 0; i < g.length; i++) {
            if (g[i] > 100 || g[i] < 0) {
                throw new WrongGradeException();
            }
            sum += g[i];
        }
        return sum / (double) (g.length);
    }
}
```

### Lab

#### Lab01: People
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/People.java)
* 那些不合理的生日

#### Lab02: Triangle
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/Triangle.java)
* 當長度是負的！

#### Lab03: LowestCommonAncestor
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/LowestCommonAncestor.java)
* 並沒有在樹上

#### Lab04: Medal
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/MedalReader.java)
* 除了金銀銅還有其他的？
* 不是數字的金銀銅？
