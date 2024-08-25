package Stepdefs;

import config.env_target;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginBDD extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        // Set Driver Location Path
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        // Maximize Driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Set URL
        driver.get(baseURL);
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-button\"]"))
        );
    }

    @When("User fill username and password")
    public void userFillUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User verify login result")
    public void userVerifyLoginResult() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
    }

    @When("User fill Invalid username and password")
    public void userFillInvalidUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("standard_use");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
        );
        driver.quit();
    }

    @When("^User input (.*) and (.*)$")
    public void userInputUsernamePassword(String username, String password) {
        driver.findElement(By.name("user-name")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @Then("User get verify login (.*)$")
    public void userGetVerifyLoginResult(String result) {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Passed") {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
            );
        } else if (result == "Failed") {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
            );
        }
    }

}
