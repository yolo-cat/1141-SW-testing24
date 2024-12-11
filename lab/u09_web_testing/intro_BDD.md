Behavior-Driven Development (BDD)
===

**Behavior-Driven Development (BDD)** 是一種敏捷軟體開發方法，強調跨職能團隊（如開發者、測試者和業務人員）之間的協作，通過自然語言（如 Gherkin 語法）編寫可執行的測試用例來驅動開發。

Cucumber 是實現 BDD 方法論的一個常見工具，它幫助團隊以清晰且無二義性的方式定義需求，確保所有成員對功能有一致的理解。以下是關於 Cucumber 和 BDD 的詳細解釋。

---

### **核心概念**

#### 1. **共同語言（Ubiquitous Language）**
   - BDD 使用自然語言（如 Gherkin 語法）描述需求，這種語言易於被開發者、業務人員和測試者理解。
   - 常用語法包括：
     - `Feature`: 功能描述。
     - `Scenario`: 測試場景。
     - `Given`, `When`, `Then`: 定義測試步驟。

   範例：
   ```gherkin
   Feature: Login functionality
     As a registered user
     I want to log into the application
     So that I can access my account

     Scenario: Successful login
       Given I am on the login page
       When I enter valid credentials
       Then I should see the dashboard
   ```

#### 2. **三層結構**
   - **業務需求（Feature File）**: 以自然語言描述需求，存放於 `.feature` 文件中。
   - **步驟定義（Step Definitions）**: 將 Gherkin 步驟映射到程式碼邏輯。
   - **應用邏輯（Application Code）**: 完成功能實作。

---

### **BDD 方法論的步驟**

#### 1. **定義需求**
   - 團隊與業務人員協作，通過範例（Scenarios）定義功能的需求。
   - 需求以 Gherkin 編寫，強調「具體例子」以減少模糊性。

#### 2. **寫 Feature File**
   - 編寫功能需求，包含多個場景（Scenarios），如正常情況、邊界情況和異常情況。

#### 3. **實作步驟定義**
   - 為 Feature File 的每一步驟撰寫對應的程式碼，使用 Cucumber 提供的標籤，例如 `@Given`, `@When`, `@Then`。
   - 範例 (Java)：
     ```java
     @Given("I am on the login page")
     public void iAmOnTheLoginPage() {
         driver.get("https://example.com/login");
     }

     @When("I enter valid credentials")
     public void iEnterValidCredentials() {
         driver.findElement(By.id("username")).sendKeys("user");
         driver.findElement(By.id("password")).sendKeys("password");
         driver.findElement(By.id("login")).click();
     }

     @Then("I should see the dashboard")
     public void iShouldSeeTheDashboard() {
         String title = driver.getTitle();
         assertEquals("Dashboard", title);
     }
     ```

#### 4. **執行測試**
   - 使用 Cucumber 執行測試，會解析 `.feature` 文件並執行對應的步驟定義程式碼。

#### 5. **迭代開發**
   - 測試失敗時，進行功能實作。
   - 測試通過後，進行重構和新需求添加，保持代碼乾淨。

---

### **Cucumber 的特點**

1. **自然語言描述需求**
   - 使用非技術人員易於理解的語言撰寫需求，方便協作。

2. **與測試框架集成**
   - Cucumber 支援多種語言和測試框架，如 Java (Junit/TestNG)、Python (Behave)、Ruby 等。

3. **重用性**
   - 相同的 Gherkin 步驟可以映射到相同的步驟定義中，減少重複工作。

4. **報告功能**
   - 提供詳細的測試結果報告，幫助團隊跟蹤進度。

---

### **BDD 的優勢**

1. **促進溝通**
   - 跨職能團隊共同定義需求，確保理解一致。

2. **提升測試覆蓋率**
   - 每個場景都會被測試，降低漏測的風險。

3. **減少返工**
   - 開發基於明確的需求，減少誤解導致的返工。

4. **可讀性**
   - Feature File 作為需求文檔，清晰易懂，便於維護。

---

### **適用場景與限制**

#### **適用場景**
- 功能需求頻繁變動的項目。
- 團隊包括技術背景薄弱的業務人員或測試者。
- 測試用例需要作為需求文檔。

#### **限制**
- 初期學習成本較高，團隊需要熟悉工具和方法論。
- 當需求過於複雜或技術性較高時，描述需求可能變得困難。
- 需要投入時間撰寫和維護步驟定義。

---

### **總結**
Cucumber 與 BDD 是強大的需求開發和測試工具，通過自然語言和範例驅動開發，幫助團隊提高軟體的質量和協作效率。如果能結合敏捷開發過程，Cucumber 可以在需求收集、測試和開發中發揮關鍵作用。


---
Demo: [BMI example](./bmi_cucumber.md)

Lab: Login in
*  請撰寫一個登入網頁，功能如下
   *  如果還沒有帳號，就申請一個，需要設定帳號密碼
   *  如果已經有了，就輸入帳號密碼，登入後會出現 <帳號>, Welcome
   *  如果忘了密碼，可以點選提示，系統要求輸入提示，如果正確就可以重新設定密碼（所以著註冊時需要填寫提示）
   *  如果密碼輸入三次沒有成功，就無法再輸入，輸入欄位會變成無法編輯; 提示也是如此
   *  ps. 可以用單純的 HTML/CSS/JS 撰寫即可，資料存在 JS 中。
*  請撰寫 Cucumber 來撰寫情境，並測試上述的網頁