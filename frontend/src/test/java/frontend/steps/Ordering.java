package frontend.steps;

import io.cucumber.java.en.Given;
import lombok.val;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ordering {

    @Given("I visit Demoblaze")
    public void i_visit_demoblaze() {
        val driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com");
        driver.quit();
    }

}
