package backend.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static backend.steps.AddPetSteps.petId;
import static io.restassured.RestAssured.given;

public class DeletePetSteps {

    @When("the user deletes Ralf")
    public static void the_user_deletes_pet () {

        given()
                .baseUri("https://petstore.swagger.io/v2")
                .pathParam("pet_id", petId)
                .when()
                .delete("/pet/{pet_id}")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Then("Ralf is deleted")
    public static void ralf_is_deleted () {

        given()
                .baseUri("https://petstore.swagger.io/v2")
                .pathParam("pet_id", petId)
                .when()
                .get("/pet/{pet_id}")
                .then()
                .assertThat()
                .statusCode(404);
    }
}
