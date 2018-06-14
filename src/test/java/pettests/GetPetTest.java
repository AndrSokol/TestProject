package pettests;

import base.AbstractPetTest;
import io.restassured.response.Response;
import models.petmodels.Pet;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetPetTest extends AbstractPetTest {

    int petId = RandomUtils.nextInt(0,9000);
    Pet expectedPet;

    @BeforeClass
    public void setUp(){
        petController.setUpRequestSettings();
        Response response = petController.addPetWithValidParameters(petId);
        petAssertions.verifyStatusCode(response, 200);
        expectedPet = response.as(Pet.class);
    }

    @Test
    public void getPetByIdTest(){
        Response response = petController.getPet(petId);

        petAssertions.verifyStatusCode(response, 200);
        petAssertions.verifyPetValues(response.as(Pet.class), expectedPet);
    }

    @Test
    public void getPetReturnsErrorWhenIdIsIncorrect(){
        Response response = petController.getPet(888888888);

        petAssertions.verifyStatusCode(response, 404);
        petAssertions.verifyErrorMessage(response, "Pet not found");
    }
}
