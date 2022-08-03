package frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static frontend.Utils.waitSeconds;

public class Ordering {

    private final ChromeDriver driver = new ChromeDriver();
    private static final Logger LOGGER = LoggerFactory.getLogger(Ordering.class);

    @Given("I visit Demoblaze")
    public void i_visit_demoblaze() {
        driver.get("https://www.demoblaze.com");

        waitSeconds(1);
    }

    @When("I click on computers section")
    public void i_visit_laptops() {
        List<WebElement> categories = driver.findElements(By.cssSelector("div.list-group a#itemc"));
        for (WebElement categorie : categories)
            if (categorie.getAttribute("onclick").contains("byCat('notebook')")) {
                categorie.click();
            }

        waitSeconds(2);
    }

    @And("I click on {} laptop")
    public void select_Sony_vaio_i5(String laptopName) {
        WebElement laptop_btn = driver.findElement(By.linkText(laptopName));
        laptop_btn.click();

        waitSeconds(1);
    }

    @And("I click on Add to cart button")
    public void add_laptop_to_cart() {
        WebElement add_to_cart_btn = driver.findElement(By.cssSelector("a.btn.btn-success.btn-lg"));
        add_to_cart_btn.click();

        waitSeconds(1);
    }

    @And("I accept pop up confirmation")
    public void accept_popup_confirmation() {
        driver.switchTo().alert().accept();

        waitSeconds(1);
    }

    @And("I navigate to laptops section")
    public void navigate_laptops_section() {
        i_visit_demoblaze();
        i_visit_laptops();

        waitSeconds(1);
    }

    @And("Now I click on {} laptop")
    public void select_Dell_i7_laptop(String laptop) {
        select_Sony_vaio_i5("Dell i7 8gb");

        waitSeconds(1);
    }

    @And("I click on Add to cart")
    public void add_Dell_to_cart() {
        add_laptop_to_cart();
        accept_popup_confirmation();

        waitSeconds(1);
    }

    @And("I navigate to cart")
    public void go_to_cart() {
        WebElement cart_btn = driver.findElement(By.id("cartur"));
        cart_btn.click();

        waitSeconds(2);
    }

    @And("I delete Dell i7 8gb laptop from cart")
    public void delete_Dell_i7() {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody#tbodyid tr"));
        table:
        for (WebElement cells : rows) {
            List<WebElement> product_name = cells.findElements(By.tagName("td"));
            for (WebElement t : product_name) {
                if (t.getAttribute("innerHTML").contains("Dell i7 8gb")) {
                    WebElement delete_btn = cells.findElement(By.linkText("Delete"));
                    delete_btn.click();
                    waitSeconds(2);
                    break table;
                }
            }
        }
    }

    @And("I click on Place Order")
    public void place_order() {
        WebElement place_order_btn = driver.findElement(By.cssSelector("button.btn.btn-success"));
        place_order_btn.click();

        waitSeconds(1);
    }

    @And("I fill in all web form fields")
    public void fill_form_fields() {
        WebElement name = driver.findElement(By.id("name"));
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

        waitSeconds(1);
    }

    @And("I click on Purchase")
    public void purchase_order() {
        List<WebElement> purchase_btn = driver.findElements(By.cssSelector("button.btn.btn-primary"));
        for (WebElement p : purchase_btn) {
            if (p.getAttribute("onclick").contains("purchaseOrder()")) {
                p.click();

                waitSeconds(1);
            }
        }
    }

    @And("I capture and log purchase Id and Amount")
    public void capture_id_and_amount() {
        String purchase_info = driver.findElement(By.cssSelector("p.lead.text-muted")).getText();
        LOGGER.info(String.format("Purchase info: \n%s\n%s", purchase_info.split("\n")[0], purchase_info.split("\n")[1]));
    }

    @And("I assert purchase amount equals expected")
    public void assert_amount() {
        String purchase_info = driver.findElement(By.cssSelector("p.lead.text-muted")).getText();
        String amount = purchase_info.split("\n")[1];
        String[] number_amount = amount.split(" ");

        String total_amount = driver.findElement(By.id("totalm")).getText();
        String[] t_amount = total_amount.split(" ");

        if(Objects.equals(number_amount[1], t_amount[1])) {
            System.out.println("Congrats! Your purchase is done correctly.");
            }
        else {
            System.out.println("Sorry, amount is not correct. Try again.");
        }
    }

    @Then("I have ordered the product correctly")
    public void iHaveOrderedTheProductCorrectly() {
        WebElement ok_btn = driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary"));
        ok_btn.click();
    }
}










