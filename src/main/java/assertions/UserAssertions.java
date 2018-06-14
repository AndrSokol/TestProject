package assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.usermodel.User;

import static org.testng.Assert.assertEquals;

public class UserAssertions {

    @Step("Verify error message")
    public void verifyErrorMessage(Response response, String messageText) {
        assertEquals(response.getBody().path("message"), messageText, "Correct error message returned");
    }

    @Step("Verify status code")
    public void verifyStatusCode(Response response, int code) {
        assertEquals(response.statusCode(), code, "Correct status code returned");
    }

    @Step("Verify user fields")
    public void verifyUserValues(User actualUser, User newUser) {
        assertEquals(actualUser.getId(), newUser.getId(), "Value id the same as expected");
        assertEquals(actualUser.getUsername(), newUser.getUsername(), "Value username the same as expected");
        assertEquals(actualUser.getFirstName(), newUser.getFirstName(), "Value firstName the same as expected");
        assertEquals(actualUser.getLastName(), newUser.getLastName(), "Value lastName the same as expected");
        assertEquals(actualUser.getEmail(), newUser.getEmail(), "Value lastName the same as expected");
        assertEquals(actualUser.getPassword(), newUser.getPassword(), "Value lastName the same as expected");
        assertEquals(actualUser.getPhone(), newUser.getPhone(), "Value lastName the same as expected");
        assertEquals(actualUser.getUserStatus(), newUser.getUserStatus(), "Value lastName the same as expected");
    }
}
