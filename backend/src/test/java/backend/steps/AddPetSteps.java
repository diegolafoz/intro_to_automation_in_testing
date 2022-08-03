package backend.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AddPetSteps {

    private String petName;
    public static String petId;
    public static String categoryId;

    @When("the user adds a new pet with {string} name, {string}, and {string} status")
    public void the_user_adds_a_new_pet_with_name_and_status(String name, String category, String status) {
        petName = given().contentType(ContentType.JSON)
                    .body(makePet(name, category, status))
                    .when().post("https://petstore.swagger.io/v2/pet")
                    .then().statusCode(200)
                    .extract().path("name");
    }

    public static String makePet(String name, String category, String status) {
        petId = String.valueOf(Math.abs(new Random().nextInt()));
        categoryId = String.valueOf(Math.abs(new Random().nextInt()));
        return String.format(
                "{\n" +
                "  \"id\": %s,\n" +
                "  \"category\": {\n" +
                "    \"id\": %s,\n" +
                "    \"name\": \"%s\"\n" +
                "  },\n" +
                "  \"name\": \"%s\",\n" +
                "  \"photoUrls\": [],\n" +
                "  \"tags\": [],\n" +
                "  \"status\": \"%s\"\n" +
                "}",
                petId,
                categoryId,
                category,
                name,
                status
        );
    }


    @Then("the pet name is {string}")
    public void thePetNameIs(String petName) {
        assertThat(petName).isEqualTo(this.petName);
    }
}
