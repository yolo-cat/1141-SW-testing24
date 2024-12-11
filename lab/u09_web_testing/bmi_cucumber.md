## **Cucumber：以 BMI 測試實例為例**

---

### **什麼是 Cucumber？**

Cucumber 是一個基於行為驅動開發（Behavior-Driven Development, BDD）的測試工具，用於撰寫與執行規格測試。  
它透過自然語言描述功能，使技術人員和非技術人員都能清楚了解需求與測試內容。  

---

### **核心概念**

1. **.feature 檔案**  
   - 用來撰寫功能規格，描述測試的場景（Scenario）。
   - 使用 Gherkin 語法（Given-When-Then）。

2. **Step Definitions（步驟定義）**  
   - 定義如何實現 .feature 檔案中每一步的執行邏輯。

3. **Runner 類別**  
   - 讓測試可以透過 JUnit 執行（適用於 JUnit 5 的情況下）。

---

### **安裝與設定**

#### **1. 建立 Maven 專案**

新增以下依賴至 `pom.xml`：  

```xml
<dependencies>
    <!-- Cucumber 與 JUnit 5 整合 -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit-platform-engine</artifactId>
        <version>7.14.0</version>
    </dependency>

    <!-- Selenium 驅動 -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.15.0</version>
    </dependency>
</dependencies>
```

---

#### **2. 設定目錄結構**

```plaintext
src
├── main
│   └── java
├── test
    ├── java
    │   └── bmi
    │       ├── MyStepdefs.java   # 步驟定義
    │       └── RunCucumberTest.java # 測試入口
    └── resources
        └── bmi
            └── bmi.feature   # 測試場景
```

---

### **範例測試：BMI 計算器**

#### **1. 實作測試場景 (`bmi.feature`)**

撰寫 .feature 檔案來描述測試情境：

```gherkin
Feature: BMI Calculator
  為了檢查 BMI 計算是否正確

  Scenario: Calculate BMI successfully
    Given I am on the BMI calculator page
    When I enter "Nick" in the name field
    And I enter "172" in the height field
    And I enter "67" in the weight field
    And I click the calculate button
    Then I should see the greeting "你好，Nick！"
    And I should see the BMI value "你的 BMI 值為：22.65"
    And I should see the BMI status "你的健康狀態：適中"

  Scenario: Clear form data
    Given I am on the BMI calculator page
    When I enter "測試者" in the name field
    And I enter "170" in the height field
    And I enter "65" in the weight field
    And I click the clear button
    Then the name field should be empty
    And the height field should be empty
    And the weight field should be empty
    And the result section should be hidden
```

---

#### **2. 實作步驟定義 (`MyStepdefs.java`)**

實現 .feature 中的每個步驟：

```java
package bmi;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MyStepdefs {

    private WebDriver driver;
    private final String testedURL = "http://127.0.0.1:5500/lab/u09_selenium/bmi.html";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the BMI calculator page")
    public void i_am_on_the_bmi_calculator_page() {
        driver.get(testedURL);
    }

    @When("I enter {string} in the name field")
    public void i_enter_in_the_name_field(String name) {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    @When("I enter {string} in the height field")
    public void i_enter_in_the_height_field(String height) {
        WebElement heightInput = driver.findElement(By.id("height"));
        heightInput.clear();
        heightInput.sendKeys(height);
    }

    @When("I enter {string} in the weight field")
    public void i_enter_in_the_weight_field(String weight) {
        WebElement weightInput = driver.findElement(By.id("weight"));
        weightInput.clear();
        weightInput.sendKeys(weight);
    }

    @When("I click the calculate button")
    public void i_click_the_calculate_button() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
    }

    @Then("I should see the greeting {string}")
    public void i_should_see_the_greeting(String expectedGreeting) {
        WebElement greeting = driver.findElement(By.id("greeting"));
        assertEquals(expectedGreeting, greeting.getText());
    }

    @Then("I should see the BMI value {string}")
    public void i_should_see_the_bmi_value(String expectedBMI) {
        WebElement bmiValue = driver.findElement(By.id("bmiValue"));
        assertEquals(expectedBMI, bmiValue.getText());
    }

    @Then("I should see the BMI status {string}")
    public void i_should_see_the_bmi_status(String expectedStatus) {
        WebElement bmiStatus = driver.findElement(By.id("bmiStatus"));
        assertEquals(expectedStatus, bmiStatus.getText());
    }

    @When("I click the clear button")
    public void i_click_the_clear_button() {
        WebElement clearButton = driver.findElement(By.xpath("//button[text()='清除資料']"));
        clearButton.click();
    }

    @Then("the name field should be empty")
    public void the_name_field_should_be_empty() {
        WebElement nameInput = driver.findElement(By.id("name"));
        assertEquals("", nameInput.getAttribute("value"));
    }

    @Then("the height field should be empty")
    public void the_height_field_should_be_empty() {
        WebElement heightInput = driver.findElement(By.id("height"));
        assertEquals("", heightInput.getAttribute("value"));
    }

    @Then("the weight field should be empty")
    public void the_weight_field_should_be_empty() {
        WebElement weightInput = driver.findElement(By.id("weight"));
        assertEquals("", weightInput.getAttribute("value"));
    }

    @Then("the result section should be hidden")
    public void the_result_section_should_be_hidden() {
        WebElement result = driver.findElement(By.id("result"));
        assertEquals("hidden", result.getAttribute("class"));
    }
}
```

---

#### **3. 設置測試入口 (`RunCucumberTest.java`)**

這個類別用來觸發 Cucumber 測試。

```java
package bmi;

import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
public class RunCucumberTest {
}
```

---

### **執行測試**
1. 右鍵 `.feature` 檔案，選擇 **Run 'Feature: ...'**。
2. 在終端機執行：
   ```bash
   mvn test
   ```

### **總結**
這份範例展示了如何整合 Cucumber、JUnit 5 與 Selenium，實現一個基於 BDD 的測試流程。希望能幫助你理解與應用 Cucumber！