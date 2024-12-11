## PMD 介紹

**PMD** 是一個靜態程式碼分析工具，主要用於檢查 Java 程式碼中的潛在問題，例如壞味道（code smells）、未使用的變數、空的 `catch` 區塊、不良的程式結構等。它幫助開發者自動檢查程式碼，提供問題建議，從而提升程式碼品質並減少潛在的錯誤。

PMD 可以與多種構建工具集成，**Maven** 是其中之一。在 Maven 專案中，我們可以透過設定 `pom.xml` 文件，使用 **PMD Maven Plugin** 來自動檢查專案中的程式碼品質。

### PMD 如何提升程式品質

PMD 能檢查程式碼中的許多常見問題，從而提升程式碼的可讀性、維護性及效能。其主要作用包括：

1. **檢測壞味道（Code Smells）**：PMD 可以發現不良的程式結構或不必要的複雜性，這些問題在後續維護過程中可能會導致錯誤或影響效能。
   
2. **強制遵循程式碼風格**：PMD 支援自定義規則，可以確保程式碼符合團隊的編碼標準。

3. **早期發現潛在錯誤**：透過靜態分析，PMD 能夠發現一些潛在的錯誤，避免在後期測試或執行時才發現。

4. **報告程式碼問題**：PMD 生成詳細的報告，指出程式碼中每個問題所在的具體行數及原因，方便開發者進行修正。

### PMD 之應用  

要在 Maven 中使用 PMD，我們需要在 `pom.xml` 中加入 **PMD Maven Plugin**。以下是一個範例配置，展示如何使用 PMD 來檢查程式碼：

#### 1. `POM.xml` 配置

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
            <version>3.15.0</version>
            <configuration>
                <rulesets>
                    <!-- 指定使用的 PMD 規則集 -->
                    <ruleset>src/main/resources/pmd/ruleset.xml</ruleset>
                </rulesets>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <!-- 執行 PMD 的 check 工作 -->
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

#### 2. 設定規則集

```xml
<?xml version="1.0" encoding="UTF-8"?>

<ruleset name="All Java Rules"
         xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">
    <description>Every Java Rule in PMD</description>
    
    <!-- the rules to be checked -->
    <rule ref="category/java/bestpractices.xml">
        <exclude name="SystemPrintln"/>
        <exclude name="AvoidReassigningParameters"/>
    </rule>
    <rule ref="category/java/codestyle.xml" />
    <rule ref="category/java/design.xml" />
    <rule ref="category/java/documentation.xml" />
    <rule ref="category/java/errorprone.xml" />
    <rule ref="category/java/multithreading.xml" />
    <rule ref="category/java/performance.xml" />
    <rule ref="category/java/security.xml" />

    <!--  override the rules  -->
    <rule ref="category/java/documentation.xml/CommentSize">
        <properties>
            <property name="maxLines" value="6" />
            <property name="maxLineLength" value="80" />
        </properties>
    </rule>
</ruleset>
```

這個規則集設定會檢查 Java 程式碼中的 `System.out.println` 語句，並在檢查到使用時產生警告。

#### 3. 執行規則檢查

在 Maven 中執行 PMD: 打開 Intellij 右方的 Maven 工具，執行 `verify` 進行檢查。

或是使用 console command: maven 中的 PMD 預設會在 `verify` 階段運行，也就是說，在執行以下指令時會自動觸發 PMD 檢查：

```bash
mvn verify
```

或者，你也可以直接執行 PMD 的檢查：

```bash
mvn pmd:check
```

這樣會檢查程式碼中的問題，並生成報告。

#### 4. 觀看報告、修正程式碼

打開 `target/report/pmd.html`, 觀看錯誤。在頁面上對於該違規如果不清楚，可以直接點擊觀看該違規，會引導到 PMD 的網站觀看說明。修正程式碼後可以再次執行 verify。

#### 5. 調整規則
如果有規則實在不符合你的需要， 例如 CommentSize 原先設定最大行數與長度分別為 6 與 80, 你可以修改：

* 修改規則參數
```xml
    <!--  override the rules  -->
    <rule ref="category/java/documentation.xml/CommentSize">
        <properties>
            <property name="maxLines" value="10" />
            <property name="maxLineLength" value="90" />
        </properties>
    </rule>
```

* 排除規格: 例如將 SystemPrintln 與 AvoidReassigningParameters 排除

```xml
    <rule ref="category/java/bestpractices.xml">
        <exclude name="SystemPrintln"/>
        <exclude name="AvoidReassigningParameters"/>
    </rule>
```

* 忽略警告
如果某些程式碼不適合檢查規則，可以透過 `@SuppressWarnings("PMD")`，讓警告不要出現在報告中。

```java
@SuppressWarnings("PMD")
public class Main {
    public static void main(String[] args) {
        // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
...        
```

詳細可以參考 [這裡](#忽略違規)

#### 管理違規

 ```xml
   <configuration>
       <rulesets>
           <ruleset>src/main/resources/pmd/ruleset.xml</ruleset>
       </rulesets>
       <failOnViolation>true</failOnViolation>
       <minimumPriority>3</minimumPriority> <!-- Set to 3 for "warning" and below -->
   </configuration>

在 PMD 的 `configuration` 部分，這兩行設定的作用如下：

1. **`<failOnViolation>true</failOnViolation>`**  
   這表示當 PMD 檢查程式碼時，如果發現任何違規（即不符合規則的程式碼），構建過程將失敗（例如 Maven build 會停止）。設定為 `true` 意味著發現的任何問題都會導致構建失敗，確保這些問題在繼續構建之前必須先行修復。

2. **`<minimumPriority>3</minimumPriority>`**  
   這設定了 PMD 規則的最低優先級。PMD 中的優先級範圍是從 1 到 5，1 表示最高優先級，5 表示最低優先級。當設置為 `minimumPriority` 為 3 時，PMD 只會報告優先級為 3 或更高的違規行為，較低優先級的違規（4 和 5）將會被忽略，不會報告。

此配置的目的是控制 PMD 檢查的嚴格程度，以及哪些問題會導致構建失敗。


If you'd like to control the behavior of PMD regarding the severity of violations, you can adjust the configuration in your `pom.xml`. Here are a few options:

1. **Set Minimum Severity**: You can set a minimum severity level that must be violated for PMD to fail the build. For example, you can specify only to fail on "error" level violations:

  
   ```

2. **Fail on Violations**: If you want to ensure that the build fails only if there are "critical" issues, you can manage this through configurations like:

   ```xml
   <configuration>
       <failOnViolation>true</failOnViolation>
       <minimumPriority>2</minimumPriority> <!-- Only fail on error and critical issues -->
   </configuration>
   ```

3. **Ignoring Certain Rules**: If some violations are not critical, consider excluding specific rules from your ruleset that generate excessive warnings or are not applicable to your project's context.

### 忽略違規

In PMD, to selectively ignore violations, such as one of the `AvoidArrayLoops` rule violations in your code, you can use **PMD suppression mechanisms**. There are three common approaches:

1. **Suppressing Violations with Annotations**
2. **Suppressing Violations with NOPMD Comments**
3. **Using Suppressions in a PMD Configuration File**

#### 1. 使用「標記」SuppressWarnings

局部方法忽略特定規則
```java
@SuppressWarnings("PMD.AvoidArrayLoops")
public void exampleMethod() {
    // Code that would normally trigger AvoidArrayLoops rule
}
```

整個 class 都忽略 PMD 的規則：
```java
@SuppressWarnings("PMD")
public class Main {
    public static void main(String[] args) {
        // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
...        
```

#### 2. 使用「註解」NOPMD

```java
for (int i = 0; i < array.length; i++) { //NOPMD - This loop is necessary for performance reasons
    // Your loop code here
}
```

The `NOPMD` comment will tell PMD to ignore the violation for that particular line. Make sure to include a reason after `//NOPMD` for better maintainability and clarity.

#### 3. 客製化規則

```xml
<rule ref="category/java/bestpractices.xml/AvoidArrayLoops">
    <exclude-pattern>.*SomeSpecificFile.java</exclude-pattern>
</rule>
```
or

```xml
<rule ref="category/java/bestpractices.xml">
    <exclude name="SystemPrintln">
        <exclude-pattern>fcu/.*\.java</exclude-pattern>
    </exclude>
    <exclude name="AvoidReassigningParameters" />
</rule>
```

### 改變 PMD 的檢查時程

PMD 預設是在 `verify` 時期執行，如果你想要讓 PMD 在其他階段（例如 `validate` 或 `compile`）運行，可以修改 `pom.xml` 中 `executions` 部分：

```xml
<execution>
    <phase>validate</phase>  <!-- 讓 PMD 在 validate 階段執行 -->
    <goals>
        <goal>check</goal>
    </goals>
</execution>
```

這樣每次執行 `mvn validate` 時就會執行 PMD 的檢查。

## Lab

### Lab01: install PMD

### Lab02: improve code
* check the report generated from PMD, improve at least 3 violations

### Lab03: customerize the rules

### Lab04: Suppress violation
* 有些部分的 violation 是被冤枉的，可以透過 NOPMD 來取消