package usertests;

import base.AbstractUserTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Regression test")
@Feature("Get user")
public class GetUserTest extends AbstractUserTest {

    @BeforeClass
    public void setUpRequestSettings(){
        userController.setUpRequestSettings();
    }

    @Description("Try to get non-existance user")
    @Story("Verify that server returns 404 when user not found")
    @Test
    public void getErrorWhenUsernameIsNotExits(){
        Response response = userController.getUserByUsername("invalid");

        userAssertions.verifyErrorMessage(response, "User not found");
        userAssertions.verifyStatusCode(response, 404);
    }
}
