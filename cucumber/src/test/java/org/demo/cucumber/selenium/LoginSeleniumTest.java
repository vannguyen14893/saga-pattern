package org.demo.cucumber.selenium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSeleniumTest {
    @ParameterizedTest
    @CsvSource({
        "1234567891,123456a@,Hello 1234567891",
        "1234567890,123456a@,Hello 1234567890"
    })
    void testLoginSuccess(String phone, String password, String message) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("http://localhost:8088");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone")));
            WebElement passwordInput = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.id("loginBtn"));

            phoneInput.clear();
            phoneInput.sendKeys(phone);
            passwordInput.clear();
            passwordInput.sendKeys(password);
            loginButton.click();

            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            assertEquals(message, messageElement.getText());

            WebElement logoutButton = driver.findElement(By.id("logoutBtn"));
            logoutButton.click();
        } finally {
            driver.quit();
        }
    }
}

