package frontend.pages;

import frontend.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class homePage {

    WebDriver driver;
    Utils utils;
    By laptops = By.xpath("//a[contains(@onclick,'notebook')]");

    public homePage(WebDriver driver) {
        this.driver = driver;
        utils = new Utils(this.driver);
    }

    public void iVisitLaptops() {
        utils.waitUntil(laptops);
        driver.findElement(laptops).click();
    }
}
