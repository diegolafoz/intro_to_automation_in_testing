package frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import static frontend.Utils.waitSeconds;

public class Ordering {

    private final ChromeDriver driver = new ChromeDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    private static final Logger LOGGER = LoggerFactory.getLogger(Ordering.class);

    @Given("I visit Demoblaze")
    public void i_visit_demoblaze() {
        driver.get("https://www.demoblaze.com");
    }

    @When("I click on computers section")
    public void i_visit_laptops() {
        WebElement laptops = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@onclick,'notebook')]")));
        laptops.click();
    }

    @And("I click on {} laptop")
    public void select_laptop(String laptopName) {
        WebElement laptopBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(laptopName)));
        laptopBtn.click();
    }

    @And("I click on Add to cart button")
    public void add_laptop_to_cart() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-success.btn-lg")));
        addToCartBtn.click();
    }

    @And("I accept pop up confirmation")
    public void accept_popup_confirmation() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @And("I navigate to laptops section")
    public void navigate_laptops_section() {
        i_visit_demoblaze();
        i_visit_laptops();
    }

    @And("Now I click on {} laptop")
    public void select_Dell_i7_laptop(String laptop) {
        select_laptop("Dell i7 8gb");
    }

    @And("I click on Add to cart")
    public void add_Dell_to_cart() {
        add_laptop_to_cart();
        accept_popup_confirmation();
    }

    @And("I navigate to cart")
    public void go_to_cart() {
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartBtn.click();
    }

    @And("I delete Dell i7 8gb laptop from cart")
    public void delete_Dell_i7() {
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tbody#tbodyid tr")));
        table:
        for (WebElement cells : rows) {
            List<WebElement> productName = cells.findElements(By.tagName("td"));
            for (WebElement t : productName) {
                if (t.getAttribute("innerHTML").contains("Dell i7 8gb")) {
                    WebElement deleteBtn = cells.findElement(By.linkText("Delete"));
                    deleteBtn.click();
                    waitSeconds(2);
                    break table;
                }
            }
        }
    }

    @And("I click on Place Order")
    public void place_order() {
        WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-success")));
        placeOrderBtn.click();
    }

    @And("I fill in all web form fields")
    public void fill_form_fields() {
        WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        name.sendKeys("Diego Lafoz Soria");
        WebElement country = driver.findElement(By.id("country"));
        country.sendKeys("Spain");
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Zaragoza");
        WebElement creditCard = driver.findElement(By.id("card"));
        creditCard.sendKeys("0000000001");
        WebElement month = driver.findElement(By.id("month"));
        month.sendKeys("January");
        WebElement year = driver.findElement(By.id("year"));
        year.sendKeys("1989");
    }

    @And("I click on Purchase")
    public void purchase_order() {
        List<WebElement> purchaseBtn = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.btn.btn-primary")));
        for (WebElement p : purchaseBtn) {
            if (p.getAttribute("onclick").contains("purchaseOrder()")) {
                p.click();
            }
        }
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
        WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")));
        okBtn.click();
    }
}










