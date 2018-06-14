package controllers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.usermodel.User;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UserController {

    public final String userBaseUri = "http://petstore.swagger.io/";
    public final String userBasePath = "v2/user";

    public UserController(){

    }

    public void setUpRequestSettings(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("api_key", "1234qwer")
                .setBaseUri(userBaseUri)
                .setBasePath(userBasePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
    }

    @Step("Create user")
    public Response createNewUser(User user){
        return given()
                .body(user)
                .when()
                .post();
    }

    @Step("Create user with empty id param")
    public Response createUserWithEmpsyId(){
        return given()
                .body("{\n" +
                        "  \"id\": ,\n" +
                        "  \"username\": \"crocop\",\n" +
                        "}")
                .when().post();
    }

    @Step("Create user with invalid id value, where id is text")
    public Response createUserWithTextId(){
        return given()
                .body("{\n" +
                        "  \"id\": \"text\",\n" +
                        "  \"username\": \"crocop\",\n" +
                        "}")
                .when().post();
    }

    @Step("Delete user")
    public Response deleteUser(String username){
        return given()
                .basePath(userBasePath + "/" + username)
                .when().delete()
                .then().extract().response();
    }

    @Step("Get user")
    public Response getUserByUsername(String username) {
        return given()//.log().all()
                .basePath(userBasePath + "/" + username)
                .accept(ContentType.JSON)
                .when().get()
                .then().extract().response();
    }

    @Step("Create user with valid params")
    public Response createUserWirhValidData(int id, String userName){
        return given()
                .body("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"username\": \"" + userName + "\",\n" +
                        "  \"firstName\": \"testFirstName\",\n" +
                        "  \"lastName\": \"testLastName\",\n" +
                        "  \"email\": \"testEmail\",\n" +
                        "  \"password\": \"testPassword\",\n" +
                        "  \"phone\": \"099911111\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when().post();
    }


}
