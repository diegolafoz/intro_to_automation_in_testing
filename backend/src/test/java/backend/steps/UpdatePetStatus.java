package backend.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import static backend.steps.AddPetSteps.categoryId;
import static backend.steps.AddPetSteps.petId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePetStatus {

    @When("the user updates Ralf status to {string}")
    public static void the_user_updates_pet_status(String status) {

        String petBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": " + categoryId + ",\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Ralf\",\n" +
                "  \"photoUrls\": [\"string\"],\n" +
                "  \"tags\": [],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";


        given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(petBody)
                .when()
                .put("/pet")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Then("Ralf status is {string}")
    public void thePetStatusIs (String status) {

        given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/" + petId)
                .then().assertThat()
                .body("status", equalTo(status));
    }
}
