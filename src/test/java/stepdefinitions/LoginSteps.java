package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage;

    @Given("user is on login page")
    public void open() {
        driver.get("https://automationexercise.com/login");
        loginPage = new LoginPage(driver);
    }

    @When("user enters username {string} and password {string}")
    public void login(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("login should be {string}")
    public void validate_login(String expected) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (expected.equalsIgnoreCase("success")) {

            wait.until(ExpectedConditions.urlToBe("https://automationexercise.com/"));

            Assert.assertEquals(driver.getCurrentUrl(),
                    "https://automationexercise.com/");

        } else {

            wait.until(ExpectedConditions.urlContains("/login"));

            Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        }
    }
}