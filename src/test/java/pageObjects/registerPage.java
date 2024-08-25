package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class registerPage {

        WebDriver driver;

        public registerPage(WebDriver driver) {
            this.driver = driver;
        }

        //Locator dari Input
        By firstNameField = By.name("customer.firstName");
        By lastNameField =  By.name("customer.lastName");

        //Metode input name
        public void inputName(String firstName, String lastName){
            //user input firstname
            driver.findElement(firstNameField).sendKeys(firstName);

            //user input lastname
            driver.findElement(lastNameField).sendKeys(lastName);
        }

}
