package authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class WordsTest {

//    String cookieED;
//    String email = "***@mail.ru";
//    String password = "******";
//
//    @BeforeClass
//    public void login(){
//        Response response = RestAssured.given()
//                .baseUri("https://www.englishdom.com/api-public/user/login/")
//                .body("{\n" +
//                        "  \"data\": {\n" +
//                        "    \"type\": \"login\",\n" +
//                        "    \"attributes\": {\n" +
//                        "      \"locale\": \"ru_RU\",\n" +
//                        "      \"email\":" + email + ",\n" +
//                        "      \"password\":" + password + "\n" +
//                        "    }\n" +
//                        "  }\n" +
//                        "}")
//                .when().post();
//
//        assertEquals(response.statusCode(), 201);
//        assertTrue(! response.getCookie("ed2").equals(null));
//        cookieED = response.getCookie("ed2");
//        //System.out.println(cookieED);
//    }
//
//    @Test
//    public void getWords(){
//        Response response = RestAssured.given().given()
//                .cookie("ed2", cookieED)
//                .baseUri("https://www.englishdom.com/api-public/word_sets/604891/words/")
//                .when().get();
//
//        System.out.println(response.getBody().asString());
//    }




}
