## PMD 介紹

**PMD** 是一個靜態程式碼分析工具，主要用於檢查 Java 程式碼中的潛在問題，例如壞味道（code smells）、未使用的變數、空的 `catch` 區塊、不良的程式結構等。它幫助開發者自動檢查程式碼，提供問題建議，從而提升程式碼品質並減少潛在的錯誤。

PMD 可以與多種構建工具集成，**Maven** 是其中之一。在 Maven 專案中，我們可以透過設定 `pom.xml` 文件，使用 **PMD Maven Plugin** 來自動檢查專案中的程式碼品質。

### PMD 如何提升程式品質

PMD 能檢查程式碼中的許多常見問題，從而提升程式碼的可讀性、維護性及效能。其主要作用包括：

1. **檢測壞味道（Code Smells）**：PMD 可以發現不良的程式結構或不必要的複雜性，這些問題在後續維護過程中可能會導致錯誤或影響效能。
   
2. **強制遵循程式碼風格**：PMD 支援自定義規則，可以確保程式碼符合團隊的編碼標準。

3. **早期發現潛在錯誤**：透過靜態分析，PMD 能夠發現一些潛在的錯誤，避免在後期測試或執行時才發現。

4. **報告程式碼問題**：PMD 生成詳細的報告，指出程式碼中每個問題所在的具體行數及原因，方便開發者進行修正。

### 使用 PMD 與 `pom.xml` 的設定

要在 Maven 中使用 PMD，我們需要在 `pom.xml` 中加入 **PMD Maven Plugin**。以下是一個範例配置，展示如何使用 PMD 來檢查程式碼：

#### 1. 基本 `pom.xml` 配置

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
                        <!-- 在構建過程中執行 PMD 的檢查 -->
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

#### 2. PMD 規則集（`ruleset.xml`）範例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ruleset name="Simple Ruleset"
    xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">
    <description>Sample PMD ruleset to demonstrate code quality checking</description>

    <!-- 設定要使用的規則，例如不允許使用 System.out.println -->
    <rule ref="category/java/bestpractices.xml/SystemPrintln" />
</ruleset>
```

這個規則集設定會檢查 Java 程式碼中的 `System.out.println` 語句，並在檢查到使用時產生警告。

#### 3. 在 Maven 中執行 PMD

Maven 中的 PMD 預設會在 `verify` 階段運行，也就是說，在執行以下指令時會自動觸發 PMD 檢查：

```bash
mvn verify
```

或者，你也可以直接執行 PMD 的檢查：

```bash
mvn pmd:check
```

這樣會檢查程式碼中的問題，並生成報告。

#### 4. 調整 PMD 的檢查範圍

如果你想要讓 PMD 在其他階段（例如 `validate` 或 `compile`）運行，可以修改 `pom.xml` 中 `executions` 部分：

```xml
<execution>
    <phase>validate</phase>  <!-- 讓 PMD 在 validate 階段執行 -->
    <goals>
        <goal>check</goal>
    </goals>
</execution>
```

這樣每次執行 `mvn validate` 時就會執行 PMD 的檢查。

### 總結

PMD 是一個強大的靜態程式碼分析工具，可以幫助開發者自動檢查程式碼中的問題，提升程式品質。在 Maven 中，透過配置 `pom.xml` 和自定義規則集，你可以根據專案的需求設定不同的程式碼檢查規則，並在構建過程中自動運行這些檢查，生成詳細的報告，便於修正程式碼中的潛在問題。



## Report

The error message indicates that PMD has found violations in your code, but it still generates a report. Here’s why that happens:

### Explanation

1. **Violations Count**: The message tells you that PMD has detected 113 rule violations in your code. This doesn't prevent the report from being generated; it merely indicates that there are issues that need attention.

2. **PMD Behavior**: PMD will run its analysis and generate reports regardless of whether it finds violations. The report is useful for developers to understand what issues exist in the codebase, even if it means the build is considered "failed" due to the violations.

3. **Build Lifecycle**: In a Maven project, the PMD check goal can be configured to fail the build based on the severity of the violations. However, the actual report (`pmd.xml` or any other format you configured) will still be created. 

### Managing Violations

If you'd like to control the behavior of PMD regarding the severity of violations, you can adjust the configuration in your `pom.xml`. Here are a few options:

1. **Set Minimum Severity**: You can set a minimum severity level that must be violated for PMD to fail the build. For example, you can specify only to fail on "error" level violations:

   ```xml
   <configuration>
       <rulesets>
           <ruleset>src/main/resources/pmd/ruleset.xml</ruleset>
       </rulesets>
       <failOnViolation>true</failOnViolation>
       <minimumPriority>3</minimumPriority> <!-- Set to 3 for "warning" and below -->
   </configuration>
   ```

2. **Fail on Violations**: If you want to ensure that the build fails only if there are "critical" issues, you can manage this through configurations like:

   ```xml
   <configuration>
       <failOnViolation>true</failOnViolation>
       <minimumPriority>2</minimumPriority> <!-- Only fail on error and critical issues -->
   </configuration>
   ```

3. **Ignoring Certain Rules**: If some violations are not critical, consider excluding specific rules from your ruleset that generate excessive warnings or are not applicable to your project's context.

### Conclusion

- The presence of violations does not prevent the report from being generated; it only indicates that issues were found.
- You can configure PMD to control whether the build should fail based on the types and severities of violations.
- Use the generated report to review and address the issues in your codebase.

If you have any specific rules causing concern, you can consider excluding them or adjusting their severity in the ruleset.