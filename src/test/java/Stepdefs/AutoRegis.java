package Stepdefs;

import config.env_target;
import pageObjects.homePage;
import pageObjects.registerPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class AutoRegis extends env_target {
    @Given("User is on parabank homepage")
    public void userIsOnParabankHomepage() {
        // Set Driver Location Path
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        // Maximize Driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Set URL
        driver.get(parabankURL);
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"leftPanel\"]/h2"))
        );
    }

    @When("User click register button")
    public void userClickRegisterButton() {
        homePage homePage = new homePage(driver);
        homePage.clickRegister();
    }

    @Then("User is in register page")
    public void userIsInRegisterPage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/h1"))
        );
    }

    @When("User input name")
    public void userInputName() {
        registerPage inputNames = new registerPage(driver);
        inputNames.inputName("Ammar", "Sungkar");
    }

    @And("User input address detail")
    public void userInputAddressDetail() {
        driver.findElement(By.name("customer.address.street")).sendKeys("Berkeley");
        driver.findElement(By.name("customer.address.city")).sendKeys("California");
        driver.findElement(By.name("customer.address.state")).sendKeys("United States");
        driver.findElement(By.name("customer.address.zipCode")).sendKeys("57556");
        driver.findElement(By.name("customer.phoneNumber")).sendKeys("029849030");
        driver.findElement(By.name("customer.ssn")).sendKeys("782828");
    }

    @And("User fill valid username and password")
    public void userFillValidUsernameAndPassword() {
        Random rand = new Random();
        int userRand = rand.nextInt(10000);
        driver.findElement(By.name("customer.username")).sendKeys("user"+userRand);
        driver.findElement(By.name("customer.password")).sendKeys("12334");
    }

    @And("User input password confirmation")
    public void userInputPasswordConfirmation() {
        driver.findElement(By.name("repeatedPassword")).sendKeys("12334");
    }

    @Then("User regist successfully")
    public void userRegistSuccessfully() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/p"))
        );
        driver.quit();
    }

    @And("user input invalid password confirmation")
    public void userInputInvalidPasswordConfirmation() {
        driver.findElement(By.name("repeatedPassword")).sendKeys("335");
    }

    @Then("User get error password did not match")
    public void userGetErrorPasswordDidNotMatch() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"repeatedPassword.errors\"]"))
        );
        driver.quit();
    }

    @When("User click register link button")
    public void userClickRegisterLinkButton() {
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click();
    }
}
