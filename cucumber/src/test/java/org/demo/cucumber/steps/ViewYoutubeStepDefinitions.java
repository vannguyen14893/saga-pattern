//package org.demo.cucumber.steps;
//
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import lombok.extern.slf4j.Slf4j;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//@Slf4j
//public class ViewYoutubeStepDefinitions {
//    private WebDriver driver;
//
//    @Before
//    public void setUp() {
//        driver = new ChromeDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
//                "AppleWebKit/537.36 (KHTML, like Gecko) " +
//                "Chrome/120.0.0.0 Safari/537.36");
//    }
//
//    @When("I open the youtube page url {string}")
//    public void i_open_youtube_page(String url) {
//        driver.get(url);
//    }
//
//    @Then("I click the {string} login button")
//    public void i_click_login(String text) {
//        driver.findElement(By.linkText(text)).click();
//    }
//
//    @Then("I enter email {string}")
//    public void i_enter_email(String email) {
//        driver.findElement(By.tagName("input")).sendKeys(email);
//    }
//
//    @Then("I enter password {string}")
//    public void i_enter_password(String password) {
//        driver.findElement(By.xpath("input")).sendKeys(password);
//    }
//
//    @Then("I click the tag name {string} button")
//    public void i_click_continue_password(String tagName) {
//        driver.findElement(By.tagName(tagName)).click();
//    }
//
//}
