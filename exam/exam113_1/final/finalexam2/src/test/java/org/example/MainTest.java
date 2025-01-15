package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.nio.channels.Selector;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {
    private WebDriver driver;
    String testedURL= "https://nlhsueh.github.io/iecs-gym/";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void main() {
        driver.get(testedURL);
        Select weekday = new Select(driver.findElement(By.xpath("//*[@id=\"day\"]")));
        Select time = new Select(driver.findElement(By.id("time")));
        WebElement age = driver.findElement(By.id("age"));
        WebElement memberYes = driver.findElement(By.id("member-yes"));
        WebElement memberNo = driver.findElement(By.id("member-no"));
        WebElement memberId = driver.findElement(By.id("member-id"));
        WebElement sendButton = driver.findElement(By.id("calculate"));


        weekday.selectByValue("Monday");
        time.selectByValue("after7");
        age.clear();
        age.sendKeys("20");
//        memberYes.clear();
//        memberYes.sendKeys("false");
        memberNo.click();
//        memberId.clear();
//        memberId.sendKeys("67");
        sendButton.click();
        WebElement Output = driver.findElement(By.id("output"));
        assertEquals("費用為 $200.00.", Output.getText());

        weekday.selectByValue("Monday");
        time.selectByValue("before7");
        age.clear();
        age.sendKeys("20");
//        memberYes.clear();
//        memberYes.sendKeys("false");
        memberNo.click();
//        memberId.clear();
//        memberId.sendKeys("67");
        sendButton.click();
        assertEquals("費用為 $160.00.", Output.getText());

        weekday.selectByValue("Saturday");
        time.selectByValue("after7");
        age.clear();
        age.sendKeys("20");
//        memberYes.clear();
//        memberYes.sendKeys("false");
        memberNo.click();
//        memberId.clear();
//        memberId.sendKeys("67");
        sendButton.click();
        assertEquals("費用為 $250.00.", Output.getText());

    }

}