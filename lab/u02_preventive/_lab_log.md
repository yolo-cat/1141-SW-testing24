## Logging

Java 提供了多種機制來記錄應用程序執行過程中的日志（log），這對於錯誤追蹤、除錯和系統監控至關重要。常用的日志框架包括 Java 標準庫中的 `java.util.logging` 和第三方的 `Log4j`、`SLF4J` 等。以下介紹 Java 的日志機制和如何在實際應用中使用。

### 1. **`java.util.logging` (JUL)**
Java 提供的內建日志框架是 `java.util.logging`（JUL）。它是一個輕量級的日誌系統，包含了不同的日誌等級和靈活的處理器，可以記錄到不同的目標（如文件、控制台）。

#### 基本使用方式
```java
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExample {
    private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        logger.info("這是一條 INFO 等級的日志訊息");
        logger.warning("這是一條 WARNING 等級的日志訊息");

        try {
            int result = 10 / 0;  // 故意製造一個錯誤
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "發生了算術異常: " + e.getMessage(), e);
        }
    }
}
```

#### 日志等級
- `SEVERE`：非常嚴重的錯誤，導致程式終止運行。
- `WARNING`：潛在問題或錯誤，但不一定會馬上影響程式。
- `INFO`：一般的運行訊息，用來記錄正常事件。
- `CONFIG`：用來記錄一些設定或配置信息。
- `FINE`, `FINER`, `FINEST`：更詳細的除錯信息，常用於開發和除錯階段。

#### 配置文件輸出
默認情況下，`java.util.logging` 會將日志輸出到控制台。你可以自定義日志輸出到文件：

1. 創建一個 `logging.properties` 文件來配置日志的輸出目標和格式：

```properties
handlers= java.util.logging.FileHandler
java.util.logging.FileHandler.pattern = log_%u.txt
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 1
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
```

2. 通過命令行參數使用配置文件：

```bash
java -Djava.util.logging.config.file=logging.properties LoggingExample
```

### 2. **Log4j**
Apache Log4j 是一個功能強大的第三方日志框架，它支持更豐富的配置選項和靈活的日志管理。Log4j 可以記錄到文件、數據庫、遠程伺服器等多種目標。

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

3. 使用 Log4j 進行日志記錄：

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
            logger.error("發生了算術異常", e);
        }
    }
}
```

### 3. **SLF4J（與 Logback）**
SLF4J（Simple Logging Facade for Java）是一個為多種日志框架提供統一接口的門面模式（facade）。Logback 是與 SLF4J 搭配使用的日志實現，性能優異。

#### SLF4J 配合 Logback 的基本使用

1. 添加 Maven 依賴：
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.10</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.32</version>
</dependency>
```

2. 創建 `logback.xml` 配置文件：
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="com.example" level="DEBUG"/>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

3. 使用 SLF4J API 進行日志記錄：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JExample {
    private static final Logger logger = LoggerFactory.getLogger(SLF4JExample.class);

    public static void main(String[] args) {
        logger.info("這是一條 INFO 級別的日誌");
        logger.warn("這是一條 WARNING 級別的日誌");

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("發生了算術異常", e);
        }
    }
}
```

### 4. **使用日志的好處**
- **追蹤問題**：通過記錄系統錯誤和異常，方便日後調試和問題排查。
- **系統監控**：日誌可以幫助監控系統運行狀況，如記錄應用程式的性能問題。
- **持久保存**：將重要訊息記錄到文件，可以方便地查閱歷史運行狀況。

### 5. **推薦做法**
- **正確選擇日志等級**：依照不同嚴重程度選擇適當的等級（INFO、ERROR、DEBUG 等）。
- **避免過多日志**：日志過多會導致性能下降，應謹慎選擇日志的輸出級別和頻率。
- **日志輪轉**：當日志文件過大時，使用日志輪轉機制（如 Log4j 和 Logback 提供的功能）自動管理日志文件。

通過有效的日志機制，可以大大提升應用的可維護性和除錯效率。


## 和 err.print() 的差異

如果只是在控制台顯示錯誤訊息，使用 `Logger` 和直接使用 `System.err.println()` 的確都能達到相似的效果，但兩者在設計目的、靈活性和功能上有很大差異。以下是一些重要的區別：

### 1. **靈活性**
- **`Logger`**：`Logger` 是一個強大的日志框架，具有更大的靈活性。你可以根據需要將日志輸出到控制台、文件、遠端伺服器或其他輸出設備，甚至可以配置不同的日志格式、日志等級、以及不同的處理器（handlers）。此外，你可以通過設定日志等級來控制不同嚴重程度的訊息是否要顯示。
- **`System.err.println()`**：這只是一個簡單的標準錯誤輸出，通常僅用來在控制台上打印錯誤訊息。它無法提供日志等級、格式化、自定義輸出目標等功能。

### 2. **日志等級**
- **`Logger`**：提供多種日志等級（如 `INFO`、`DEBUG`、`WARNING`、`SEVERE` 等），這使得你可以根據不同的需求來過濾和記錄特定等級的日志。這樣，在生產環境中，你可以只記錄 `SEVERE` 和 `WARNING` 級別的錯誤，而在開發過程中可以記錄更詳細的 `DEBUG` 或 `FINE` 級別的日志。
- **`System.err.println()`**：沒有日志等級概念，所有的輸出都是以同樣的方式處理，無法區分不同嚴重程度的錯誤或事件。

### 3. **格式化**
- **`Logger`**：可以配置不同的日志格式器（formatter），比如時間戳、類名、執行緒名等資訊，這些訊息在調試和排錯時非常有用。你可以自定義輸出的格式以便更清晰地閱讀和分析。
- **`System.err.println()`**：輸出格式無法自定義，只能簡單地輸出文字，無法提供額外的上下文訊息如時間戳或錯誤來源。

### 4. **配置與控制**
- **`Logger`**：可以根據需要通過配置文件或程式設置來動態改變日志行為，例如控制日志是否寫入文件，改變輸出格式，調整日志等級等。這使得系統可以在不修改程式碼的情況下進行日志行為的調整。
- **`System.err.println()`**：完全無法配置，並且必須直接修改程式碼才能改變輸出內容。

### 5. **多目標日志輸出**
- **`Logger`**：可以同時將日志輸出到多個目標，例如控制台和文件，或甚至遠程伺服器等，這對於大規模應用的錯誤追蹤和系統監控非常有用。
- **`System.err.println()`**：只能輸出到控制台的標準錯誤流，無法擴展到其他目標。

### 6. **性能**
- **`Logger`**：在大規模應用中，日志框架如 `java.util.logging`、`Log4j`、`Logback` 等，通過異步記錄或日誌輪轉等技術，能有效管理大量日志的輸出，從而降低性能損耗。
- **`System.err.println()`**：每次都同步地直接輸出到控制台，當記錄大量訊息時，性能可能會變差，尤其是在生產環境中不需要那麼詳細的輸出時，這會消耗資源。

### 7. **錯誤處理**
- **`Logger`**：可以配置不同的處理器來應對不同情境的錯誤處理。例如，可以將錯誤記錄到一個單獨的錯誤日志文件中，這樣可以更容易地追蹤問題。此外，還能配置備份、限制日志文件大小等功能。
- **`System.err.println()`**：只能簡單地打印出錯誤，並無法進行更細緻的錯誤處理或記錄。

### 總結
使用 `Logger` 能提供更多功能和靈活性，尤其是在開發大規模或複雜應用時，便於記錄不同類型的訊息並可擴展日志輸出的目的地。而 `System.err.println()` 只是用於簡單的錯誤輸出，適合於臨時或非常小的程式調試。如果你需要更有條理、更靈活的日志管理，推薦使用 `Logger` 或第三方的日志框架如 Log4j、SLF4J 等。

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

## Lab

### Lab01: Medal
* [參考程式碼](../../Intellij/DemoPreventive/src/main/java/xdemo/MedalReader.java)
* 除了金銀銅還有其他的？
* 不是數字的金銀銅？
* 用 Log 把錯誤寫到檔案中


