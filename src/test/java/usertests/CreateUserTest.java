package usertests;

import base.AbstractUserTest;
import base.CsvDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.usermodel.User;
import java.util.Map;

@Epic("Regression test")
@Feature("Create new user")
public class CreateUserTest extends AbstractUserTest {

    @BeforeClass
    public void setUpRequestSettings(){
        userController.setUpRequestSettings();
    }

    @Description("Creation user with valid parameters")
    @Story("Verify that user can be created with set of valid parameters")
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void createUserWithDifferentParamTest(Map<String, String> testData){
        User newUser = new User(testData);
        System.out.println(newUser.toString());

        Response response = userController.createNewUser(newUser);
        userAssertions.verifyStatusCode(response, 200);

        User actualUser = userController.getUserByUsername(newUser.getUsername()).as(User.class);
        System.out.println(actualUser.toString());
        System.out.println(newUser.getUsername());
        System.out.println(actualUser.getUsername());
        userAssertions.verifyUserValues(actualUser, newUser);
    }

    @Description("Negative test. Creation user with invalid id, where id is empty")
    @Story("Verify that server returns error after request on creation user with empty id")
    @Test
    public void verifyErrorWhenIdIsEmpty(){
        Response response = userController.createUserWithEmpsyId();

        userAssertions.verifyStatusCode(response, 400);
        userAssertions.verifyErrorMessage(response, "bad input");
    }

    @Description("Negative test. Creation user with invalid id, where id is text value")
    @Story("Verify that server returns error after request on creation user with text value in id parameter")
    @Test
    public void verifyErrorWhenIdIsText(){
        Response response = userController.createUserWithTextId();

        System.out.println(response.statusCode());
        userAssertions.verifyStatusCode(response, 500);
        userAssertions.verifyErrorMessage(response, "something bad happened");
    }
}
