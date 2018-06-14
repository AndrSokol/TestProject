package pettests;

import base.AbstractPetTest;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeletePetTest extends AbstractPetTest {
    int petId = RandomUtils.nextInt(0,9000);

    @BeforeClass
    public void setUp(){
        petController.setUpRequestSettings();
        Response response = petController.addPetWithValidParameters(petId);
        petAssertions.verifyStatusCode(response, 200);
   }

    @Test
    public void deletePetByIdTest(){
        Response deleteResponse = petController.deletePet(petId);
        petAssertions.verifyStatusCode(deleteResponse, 200);

        Response getResponse = petController.getPet(petId);
        petAssertions.verifyStatusCode(getResponse, 404);
    }

    @Test
    public void deletePetByIncorrectIdTest(){
        int petId = RandomUtils.nextInt(999900,999999);
        Response deleteResponse = petController.deletePet(petId);
        petAssertions.verifyStatusCode(deleteResponse, 404);
    }
}
