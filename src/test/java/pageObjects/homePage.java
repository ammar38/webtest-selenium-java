package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class homePage {
    WebDriver driver;

    public homePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator dari Regis Button
    By RegisterButton = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    //Metode Click Register Button
    public void clickRegister(){
        driver.findElement(RegisterButton).click();
    }
}
