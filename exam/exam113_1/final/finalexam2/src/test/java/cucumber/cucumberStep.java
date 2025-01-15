package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class cucumberStep {
    private WebDriver driver;
    String testedURL= "https://nlhsueh.github.io/iecs-gym/";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the Gymfee calculator page")
    public void i_am_on_the_Gymfee_calculator_page() {
        driver.get(testedURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I select {string}")
    public void i_select_monday(String Weekday) {
        Select weekday = new Select(driver.findElement(By.xpath("//*[@id=\"day\"]")));
        weekday.selectByValue(Weekday);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I select before 7")
    public void i_select_before_7() {
        Select time = new Select(driver.findElement(By.id("time")));
        time.selectByValue("before7");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I select after 7")
    public void i_select_after_7() {
        Select time = new Select(driver.findElement(By.id("time")));
        time.selectByValue("after7");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I send age {string}")
    public void i_send_age_20(String Age) {
        WebElement age = driver.findElement(By.id("age"));
        age.click();
        age.sendKeys(Age);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I select member")
    public void i_select_member() {
        WebElement memberYes = driver.findElement(By.id("member-yes"));
        memberYes.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I select not member")
    public void i_select_not_member() {
        WebElement memberNo = driver.findElement(By.id("member-no"));
        memberNo.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I send memberid {string}")
    public void i_send_member_id(String Id) {
        WebElement id = driver.findElement(By.id("member-id"));
        id.clear();
        id.sendKeys(Id);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @When("I click sendButton")
    public void i_click_sendButton() {
        WebElement sendButton = driver.findElement(By.id("calculate"));
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }
    @Then("I should see the output is {string}")
    public void i_should_see_the_output_is(String expectedOutput) {
        WebElement output = driver.findElement(By.id("output"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        assertEquals(expectedOutput, output.getText());
    }

}