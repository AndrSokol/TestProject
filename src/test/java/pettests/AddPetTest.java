package pettests;

import base.AbstractPetTest;
import base.CsvDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.petmodels.Pet;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class AddPetTest extends AbstractPetTest {

    @BeforeClass
    public void setUpRequestSettings(){
        petController.setUpRequestSettings();
    }

    @Test (dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void addPetTest(Map<String, String> testData){
        Pet newPet = new Pet(testData);

        Response response = petController.addPet(newPet);
        Pet actualPet = response.as(Pet.class);

        petAssertions.verifyStatusCode(response, 200);
        petAssertions.verifyPetValues(actualPet, newPet);
    }

    @Test
    public void validateHeader(){
        int idTestValue = RandomUtils.nextInt(0,9000);
        Response response = petController.addPetWithValidParameters(idTestValue);

        String allowMethods = "GET, POST, DELETE, PUT";
        assertEquals(response.getHeader("Access-Control-Allow-Methods"), allowMethods, "Correct allow methods returned");

        String allowHeaders = "Content-Type, api_key, Authorization";
        assertEquals(response.getHeader("Access-Control-Allow-Headers"), allowHeaders, "Correct allow headers returned");

        String serverName = "Jetty(9.2.9.v20150224)";
        assertEquals(response.getHeader("Server"), serverName, "Correct server returned");

        String contentType = ContentType.JSON.toString();
        assertEquals(response.getHeader("Content-Type"), contentType, "Correct content type returned");
    }

    @Test
    public void addNewWithMinimumParameters(){
        int idTestValue = RandomUtils.nextInt(0,9000);
        Pet testPet = new Pet(idTestValue);

        int actualId = petController.addPet(testPet).path("id");

        assertEquals(actualId, idTestValue, "Correct pet id");
    }

    @Test
    public void addNewPetWithEmptyIdTest(){
        Response response = petController.addPetWithEmptyId();

        petAssertions.verifyStatusCode(response, 400);
        petAssertions.verifyErrorMessage(response, "bad input");
    }
}
