package assertions;

import io.restassured.response.Response;
import models.petmodels.Pet;

import static org.testng.Assert.assertEquals;

public class PetAssertions {
    public void verifyErrorMessage(Response response, String messageText) {
        assertEquals(response.getBody().path("message"), messageText, "Correct error message returned");
    }

    public void verifyStatusCode(Response response, int code) {
        assertEquals(response.statusCode(), code, "Correct status code returned");
    }

    public void verifyPetValues(Pet actualPet, Pet newPet) {
        assertEquals(actualPet.getId(), newPet.getId(), "Field Id is correct");
        assertEquals(actualPet.getCategory().getId(), newPet.getCategory().getId(), "Field Category/id is correct");
        assertEquals(actualPet.getCategory().getName(), newPet.getCategory().getName(), "Field Category/name is correct");
        assertEquals(actualPet.getName(), newPet.getName(), "Field name is correct");
        assertEquals(actualPet.getPhotoUrls().get(0), newPet.getPhotoUrls().get(0), "Field photo url (first) is correct");
        assertEquals(actualPet.getTags().get(0).getId(), newPet.getTags().get(0).getId(), "Field tags/id is correct");
        assertEquals(actualPet.getTags().get(0).getName(), newPet.getTags().get(0).getName(), "Field tags/name is correct");
        assertEquals(actualPet.getStatus(), newPet.getStatus(), "Field status is correct");
    }
}
