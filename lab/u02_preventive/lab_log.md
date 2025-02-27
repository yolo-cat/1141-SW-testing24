## Logging

Java 提供了多種機制來記錄應用程序執行過程中的日誌（log），這對於錯誤追蹤、除錯和系統監控至關重要。常用的日誌框架包括 Java 標準庫中的 `java.util.logging` 和第三方的 `Log4j`、`SLF4J` 等。以下介紹 Java 的日誌機制和如何在實際應用中使用。

### 1. **`java.util.logging` (JUL)**
Java 提供的內建日誌框架是 `java.util.logging`（JUL）。它是一個輕量級的日誌系統，包含了不同的日誌等級和靈活的處理器，可以記錄到不同的目標（如文件、控制台）。

#### 基本使用方式
```java
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExample {
    // 一般都是用 class name 來作為 logger 的名字
    private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        logger.info("這是一條 INFO 等級的日誌訊息");
        logger.warning("這是一條 WARNING 等級的日誌訊息");

        try {
            int result = 10 / 0;  // 故意製造一個錯誤
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "發生了算術異常: " + e.getMessage(), e);
        }
    }
}
```

#### 日誌等級
- `SEVERE`：非常嚴重的錯誤，導致程式終止運行。
- `WARNING`：潛在問題或錯誤，但不一定會馬上影響程式。
- `INFO`：一般的運行訊息，用來記錄正常事件。
- `CONFIG`：用來記錄一些設定或配置訊息。
- `FINE`, `FINER`, `FINEST`：更詳細的除錯日誌，常用於開發和除錯階段。

### 2. **Log4j**
Apache Log4j 是一個功能強大的第三方日誌框架，它支持更豐富的配置選項和靈活的日誌管理。Log4j 可以記錄到文件、資料庫、遠程伺服器等多種目標。

#### Log4j 2 的基本使用

1. 添加 Maven 依賴：
```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.17.0</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.17.0</version>
</dependency>
```

2. 創建 `log4j2.xml` 配置文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <File name="LogFile" fileName="app.log">
            <PatternLayout>
                <pattern>%d %p %c [%t] %m%n</pattern>
            </PatternLayout>
        </File>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
            <AppenderRef ref="LogFile" />
        </Root>
    </Loggers>
</Configuration>
```

3. 使用 Log4j 進行日誌記錄：

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jExample {
    private static final Logger logger = LogManager.getLogger(Log4jExample.class);

    public static void main(String[] args) {
        logger.info("這是一條 INFO 級別的日誌");
        logger.warn("這是一條 WARNING 級別的日誌");

        try {
            int result = 10 / 0;  // 製造一個錯誤
        } catch (ArithmeticException e) {
            logger.error("發生了計算異常", e);
        }
    }
}
```

### 4. **使用日志的好處**
- **追蹤問題**：通過記錄系統錯誤和異常，方便日後調試和問題排查。
- **系統監控**：日誌可以幫助監控系統運行狀況，如記錄應用程式的性能問題。
- **持久保存**：將重要訊息記錄到文件，可以方便地查閱歷史運行狀況。

### 5. **推薦做法**
- **正確選擇日誌等級**：依照不同嚴重程度選擇適當的等級（INFO、ERROR、DEBUG 等）。
- **避免過多日誌**：日志過多會導致性能下降，應謹慎選擇日誌的輸出級別和頻率。
- **日誌輪轉**：當日誌文件過大時，使用日誌輪轉機制（如 Log4j 和 Logback 提供的功能）自動管理日誌文件。

通過有效的日誌機制，可以大大提升應用的可維護性和除錯效率。

## 和 err.print() 的差異

如果只是在控制台顯示錯誤訊息，使用 `Logger` 和直接使用 `System.err.println()` 的確都能達到相似的效果，但兩者在設計目的、靈活性和功能上有很大差異。以下是一些重要的區別：

### 1. **靈活性**
- **`Logger`**：`Logger` 是一個強大的日誌框架，具有更大的靈活性。你可以根據需要將日誌輸出到控制台、文件、遠端伺服器或其他輸出設備，甚至可以配置不同的日誌格式、日誌等級、以及不同的處理器（handlers）。此外，你可以通過設定日誌等級來控制不同嚴重程度的訊息是否要顯示。
- **`System.err.println()`**：這只是一個簡單的標準錯誤輸出，通常僅用來在控制台上印出錯誤訊息。它無法提供日誌等級、格式化、自定義輸出目標等功能。

### 2. **日誌等級**
- **`Logger`**：提供多種日誌等級（如 `INFO`、`DEBUG`、`WARNING`、`SEVERE` 等），這使得你可以根據不同的需求來過濾和記錄特定等級的日誌。這樣，在生產環境中，你可以只記錄 `SEVERE` 和 `WARNING` 級別的錯誤，而在開發過程中可以記錄更詳細的 `DEBUG` 或 `FINE` 級別的日誌。
- **`System.err.println()`**：沒有日誌等級概念，所有的輸出都是以同樣的方式處理，無法區分不同嚴重程度的錯誤或事件。

### 3. **格式化**
- **`Logger`**：可以配置不同的日誌格式器（formatter），比如時間戳、類名、執行緒名等資訊，這些訊息在調試和排錯時非常有用。你可以自定義輸出的格式以便更清晰地閱讀和分析。
- **`System.err.println()`**：輸出格式無法自定義，只能簡單地輸出文字，無法提供額外的上下文訊息如時間戳或錯誤來源。

### 4. **配置與控制**
- **`Logger`**：可以根據需要通過配置文件或程式設置來動態改變日誌行為，例如控制日誌是否寫入文件，改變輸出格式，調整日誌等級等。這使得系統可以在不修改程式碼的情況下進行日誌行為的調整。
- **`System.err.println()`**：完全無法配置，並且必須直接修改程式碼才能改變輸出內容。

### 5. **多目標日誌輸出**
- **`Logger`**：可以同時將日誌輸出到多個目標，例如控制台和文件，或甚至遠程伺服器等，這對於大規模應用的錯誤追蹤和系統監控非常有用。
- **`System.err.println()`**：只能輸出到控制台的標準錯誤流，無法擴展到其他目標。

### 6. **性能**
- **`Logger`**：在大規模應用中，日誌框架如 `java.util.logging`、`Log4j`、`Logback` 等，通過異步記錄或日誌輪轉等技術，能有效管理大量日誌的輸出，從而降低性能損耗。
- **`System.err.println()`**：每次都同步地直接輸出到控制台，當記錄大量訊息時，性能可能會變差，尤其是在生產環境中不需要那麼詳細的輸出時，這會消耗資源。

### 7. **錯誤處理**
- **`Logger`**：可以配置不同的處理器來應對不同情境的錯誤處理。例如，可以將錯誤記錄到一個單獨的錯誤日志文件中，這樣可以更容易地追蹤問題。此外，還能配置備份、限制日誌文件大小等功能。
- **`System.err.println()`**：只能簡單地印出錯誤，並無法進行更細緻的錯誤處理或記錄。

### 總結
使用 `Logger` 能提供更多功能和靈活性，尤其是在開發大規模或複雜應用時，便於記錄不同類型的訊息並可擴展日誌輸出的目的地。而 `System.err.println()` 只是用於簡單的錯誤輸出，適合於臨時或非常小的程式調試。如果你需要更有條理、更靈活的日志管理，推薦使用 `Logger` 或第三方的日志框架如 Log4j、SLF4J 等。

## 寫到檔案

```java
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingExample {
    private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        try {
            // 設定 FileHandler，將日誌寫入檔案 "app.log"
            FileHandler fileHandler = new FileHandler("app.log", true); // true 表示追加到文件中
            fileHandler.setFormatter(new SimpleFormatter()); // 設定格式為簡單格式
            logger.addHandler(fileHandler);

            logger.info("這是一條 INFO 等級的日誌訊息");
            logger.warning("這是一條 WARNING 等級的日誌訊息");

            // 故意產生一個錯誤
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "發生了算術異常: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "無法創建 FileHandler: " + e.getMessage(), e);
        }
    }
}
```

## Ex

### **📌 Java `java.util.logging` 練習題**
Java 提供 `java.util.logging`（JUL）作為內建的日誌記錄工具。這些練習題幫助學生學習如何在 Java 程式中使用 `Logger` 來記錄資訊、例外錯誤及日誌格式化。

---

### **🎯 練習題 1：基本日誌記錄**
**題目描述：**  
請撰寫一個 Java 程式，使用 `java.util.logging.Logger` 來記錄不同等級的日誌（`INFO`、`WARNING`、`SEVERE`）。

**要求：**
1. 創建 `Logger` 物件，命名為 `"MyLogger"`。
2. 記錄以下級別的日誌：
   - `INFO`："系統啟動成功"
   - `WARNING`："可能的記憶體不足警告"
   - `SEVERE`："系統崩潰！"
3. 執行程式後，檢查 console 輸出結果。

**提示：**
- 使用 `Logger.getLogger()` 取得 Logger 物件。
- 用 `logger.info()`、`logger.warning()`、`logger.severe()` 記錄日誌。

---

### **🎯 練習題 2：記錄例外錯誤**
**題目描述：**  
請撰寫一個 Java 程式，計算兩個整數的相除結果，並使用 `Logger` 記錄計算過程。如果發生 `ArithmeticException`（如除以零），則記錄 `SEVERE` 級別的錯誤。

**要求：**
1. 使用 `Scanner` 讀取兩個整數，並嘗試執行除法運算。
2. 如果除數為 0，捕獲 `ArithmeticException`，並使用 `logger.log(Level.SEVERE, "錯誤：除數不能為零", e);` 記錄例外錯誤。
3. 如果沒有錯誤，記錄 `INFO` 級別的運算結果。

**提示：**
- 使用 `try-catch` 捕獲異常，並用 `logger.log(Level.SEVERE, msg, exception)` 記錄錯誤。

---

### **🎯 練習題 3：將日誌輸出到檔案**
**題目描述：**  
請撰寫一個 Java 程式，讓 `Logger` 的輸出不僅顯示在 console，還要寫入到 `app.log` 檔案中。

**要求：**
1. 設定 `Logger` 物件，使其輸出到 `app.log` 檔案。
2. 記錄 `INFO`、`WARNING` 和 `SEVERE` 級別的訊息。
3. 執行程式後，檢查 `app.log` 檔案內容是否正確。

**提示：**
- 使用 `FileHandler` 來設定日誌輸出檔案：
  ```java
  FileHandler fileHandler = new FileHandler("app.log", true);
  logger.addHandler(fileHandler);
  ```
- 使用 `SimpleFormatter` 讓輸出內容較易讀：
  ```java
  fileHandler.setFormatter(new SimpleFormatter());
  ```

---

這些練習題幫助學生理解 `java.util.logging` 的基礎概念，包括：
1. **如何記錄不同等級的日誌** (`INFO`、`WARNING`、`SEVERE`)。
2. **如何記錄例外錯誤** (`logger.log(Level.SEVERE, msg, exception)`)。
3. **如何將日誌輸出到檔案** (`FileHandler` + `SimpleFormatter`)。

你可以進一步擴展這些練習，例如讓學生設定 `Logger` 的日誌等級、使用 `XMLFormatter`，或建立自訂的日誌處理器（Handler）。🚀


