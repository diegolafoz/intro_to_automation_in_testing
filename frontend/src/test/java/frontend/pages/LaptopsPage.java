package frontend.pages;

import frontend.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaptopsPage {
    WebDriver driver;
    Utils utils;

    public LaptopsPage(WebDriver driver) {
        this.driver = driver;
        utils = new Utils(this.driver);
    }

    public void select_laptop(String laptopName){
        By laptopSelected = By.linkText(laptopName);
        utils.waitUntil(laptopSelected);
        driver.findElement(laptopSelected).click();
    }
}
