package org.demo.cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class LoginStepDefinitions {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Given("I open the login page url {string}")
    public void i_open_login_page(String url) {
        driver.get(url);
    }

    @When("I enter phone {string} and password {string}")
    public void i_login(String username, String password) {
        driver.findElement(By.name("phone")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        WebElement loginButton = driver.findElement(By.tagName("button"));
        WebElement checkBoxShowPassword = driver.findElement(By.id("show-password"));
        WebElement checkBoxRemember = driver.findElement(By.id("remember"));
        new Actions(driver)
                .moveToElement(checkBoxShowPassword)
                .click()
                .clickAndHold(checkBoxRemember)
                .click()
                .moveToElement(loginButton)
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
    }

    @Then("I should see the {string} on page")
    public void i_should_see_homepage(String message) {
        WebElement messageWebElement = driver.findElement(By.id("message"));
        assertEquals(messageWebElement.getText(), message);
    }

    @Then("I click button logout")
    public void i_click_logout() throws InterruptedException {
        WebElement logoutButton = driver.findElement(By.tagName("button"));
        new Actions(driver).moveToElement(logoutButton)
                .pause(Duration.ofSeconds(3))
                .click()
                .perform();
    }
}
