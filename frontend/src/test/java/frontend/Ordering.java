package frontend;

import frontend.pages.cartPage;
import frontend.pages.homePage;
import frontend.pages.laptopsPage;
import frontend.pages.specificLaptopPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class Ordering {

    private final ChromeDriver driver = new ChromeDriver();
    private static final Logger LOGGER = LoggerFactory.getLogger(Ordering.class);
    homePage home;
    laptopsPage laptop;
    specificLaptopPage specific;
    cartPage cart;
    Utils utils;

    @Given("I visit Demoblaze")
    public void i_visit_demoblaze() {
        driver.get("https://www.demoblaze.com");
    }

    @When("I click on computers section")
    public void i_visit_laptops() {
        home = new homePage(driver);
        home.iVisitLaptops();
    }

    @And("I click on {} laptop")
    public void i_click_on_laptop(String laptopName) {
        laptop = new laptopsPage(driver);
        laptop.select_laptop(laptopName);
    }

    @And("I click on Add to cart button")
    public void add_laptop_to_cart() {
        specific = new specificLaptopPage(driver);
        specific.clickAddBtn();
    }

    @And("I accept pop up confirmation")
    public void accept_popup_confirmation() {
        utils = new Utils(driver);
        utils.waitAlert();
        driver.switchTo().alert().accept();
    }

    @And("I navigate to laptops section")
    public void navigate_laptops_section() {
        home = new homePage(driver);
        i_visit_demoblaze();
        home.iVisitLaptops();
    }

    @And("Now I click on {} laptop")
    public void select_Dell_i7_laptop(String laptopName) {
        laptop = new laptopsPage(driver);
        laptop.select_laptop(laptopName);
    }

    @And("I click on Add to cart")
    public void add_Dell_to_cart() {
        specific = new specificLaptopPage(driver);
        specific.clickAddBtn();
        accept_popup_confirmation();
    }

    @And("I navigate to cart")
    public void go_to_cart() {
        specific = new specificLaptopPage(driver);
        specific.clickCartBtn();
    }

    @And("I delete Dell i7 8gb laptop from cart")
    public void delete_Dell_i7() {
        cart = new cartPage(driver);
        cart.deleteLaptop();
    }

    @And("I click on Place Order")
    public void place_order() {
        cart = new cartPage(driver);
        cart.clickPlaceOrderBtn();
    }

    @And("I fill in all web form fields")
    public void fill_form_fields() {
        cart = new cartPage(driver);
        cart.enterName("Diego");
        cart.enterCountry("Spain");
        cart.enterCity("Zaragoza");
        cart.enterCreditCard("123456");
        cart.enterMonth("January");
        cart.enterYear("1989");
    }

    @And("I click on Purchase")
    public void purchase_order() {
        cart = new cartPage(driver);
        cart.clickPurchaseOrder();
    }

    @And("I capture and log purchase Id and Amount")
    public void capture_id_and_amount() {
        String purchaseInfo = driver.findElement(By.cssSelector("p.lead.text-muted")).getText();
        LOGGER.info(String.format("Purchase info: \n%s\n%s", purchaseInfo.split("\n")[0], purchaseInfo.split("\n")[1]));
    }

    @And("I assert purchase amount equals expected")
    public void assert_amount() {
        String purchaseInfo = driver.findElement(By.cssSelector("p.lead.text-muted")).getText();
        String amount = purchaseInfo.split("\n")[1];
        String[] numberAmount = amount.split(" ");

        String totalAmount = driver.findElement(By.id("totalm")).getText();
        String[] tAmount = totalAmount.split(" ");

        if (Objects.equals(numberAmount[1], tAmount[1])) {
            System.out.println("Congrats! Your purchase is done correctly.");
        } else {
            System.out.println("Sorry, amount is not correct. Try again.");
        }
    }

    @Then("I have ordered the product correctly")
    public void i_have_ordered_the_product_correctly() {
        cart = new cartPage(driver);
        cart.clickOk();
    }
}