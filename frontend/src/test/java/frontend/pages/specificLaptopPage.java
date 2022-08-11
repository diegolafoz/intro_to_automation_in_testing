package frontend.pages;

import frontend.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class specificLaptopPage {

    WebDriver driver;
    Utils utils;
    By addToCartBtn = By.cssSelector("a.btn.btn-success.btn-lg");
    By cartBtn = By.id("cartur");

    public specificLaptopPage (WebDriver driver) {
        this.driver = driver;
        utils = new Utils(this.driver);
    }

    public void clickAddBtn() {
        utils.waitUntil(addToCartBtn);
        driver.findElement(addToCartBtn).click();
    }

    public void clickCartBtn() {
        utils.waitUntil(cartBtn);
        driver.findElement(cartBtn).click();
    }
}
