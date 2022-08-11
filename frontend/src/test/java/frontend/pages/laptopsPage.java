package frontend.pages;

import frontend.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class laptopsPage {
    WebDriver driver;
    Utils utils;

    public laptopsPage(WebDriver driver) {
        this.driver = driver;
        utils = new Utils(this.driver);
    }

    public void select_laptop(String laptopName){
        By laptopSelected = By.linkText(laptopName);
        utils.waitUntil(laptopSelected);
        driver.findElement(laptopSelected).click();
    }
}
