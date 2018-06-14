package usertests;

import base.AbstractUserTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Regression test")
@Feature("Delete user")
public class DeleteUserTest extends AbstractUserTest {
    int userId = RandomUtils.nextInt(0,9000);
    String userName = RandomStringUtils.randomAlphabetic(10);

    @BeforeClass
    public void setUp(){
        userController.setUpRequestSettings();
        Response response = userController.createUserWirhValidData(userId, userName);
        userAssertions.verifyStatusCode(response, 200);
    }

    @Description("Delete user")
    @Story("Verify that user can be deleted correctly")
    @Test
    public void deleteUserTest(){
        Response deleteUserResponse = userController.deleteUser(userName);
        userAssertions.verifyStatusCode(deleteUserResponse, 200);

        Response getUserResponse = userController.getUserByUsername(userName);
        userAssertions.verifyStatusCode(getUserResponse, 404);
    }

    @Description("Try to delete invalid user")
    @Story("Verify that server returns error after trying to delete user by non-existance username")
    @Test
    public void verifyErrorWhenUserNotExist(){
        String deletedUserName = RandomStringUtils.randomAlphabetic(10);
        Response response = userController.deleteUser(deletedUserName);
        userAssertions.verifyStatusCode(response, 404);
    }
}
