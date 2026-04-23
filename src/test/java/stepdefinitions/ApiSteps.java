package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiSteps {

    Response response;

    @Given("API signup is triggered")
    public void api_signup() {

        // Unique email each time to avoid duplicate user creation error
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";

        response = RestAssured
                .given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("name", "testuser")
                .formParam("email", uniqueEmail)
                .formParam("password", "Test@123")
                .formParam("title", "Mr")
                .formParam("birth_date", "10")
                .formParam("birth_month", "1")
                .formParam("birth_year", "2000")
                .formParam("firstname", "Test")
                .formParam("lastname", "User")
                .formParam("company", "Capgemini")
                .formParam("address1", "India")
                .formParam("address2", "Nagpur")
                .formParam("country", "India")
                .formParam("zipcode", "440001")
                .formParam("state", "Maharashtra")
                .formParam("city", "Nagpur")
                .formParam("mobile_number", "1234567890")
                .post("https://automationexercise.com/api/createAccount");

        System.out.println("Response Body: " + response.asString());
    }

    @Then("API response should be success")
    public void validate_response() {

        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");

        System.out.println("Response Code (API): " + responseCode);
        System.out.println("Message: " + message);

        Assert.assertEquals(responseCode, 201, "API responseCode mismatch");
        Assert.assertEquals(message, "User created!", "Message mismatch");
    }
}