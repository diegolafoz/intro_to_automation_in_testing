package backend.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPetsSteps {

    @When("the user gets pets with available status")
    public void the_user_gets_available_pets () {

        given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .then()
                .assertThat()
                .statusCode(200);

        given()
                .log().all()
                .when()
                    .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .then()
                .log().body();
    }

    @Then("the pets status is {string}")
    public void the_pets_status_is (String status) {

        given()
                .when()
                    .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .then()
                    .assertThat()
                .body("status", everyItem(is((status))));
    }
}
