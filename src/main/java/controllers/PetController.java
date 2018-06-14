package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.petmodels.Category;
import models.petmodels.Pet;
import models.petmodels.TagsItem;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetController {

    public final String baseUri = "http://petstore.swagger.io/";
    public final String basePath = "v2/pet";
    public final String addPetPath = basePath + "/addpet";

    public PetController(){

    }

    public void setUpRequestSettings(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("api_key", "1234qwer")
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
    }

    public Response addPet(Pet newPet) {
        return given()
                .body(newPet)
                .when().post();
    }

    public Response addPetWithValidParameters(int idTestValue){
        int id = idTestValue;
        Category category = new Category(5, "Dogs");
        String name = "dog";
        List<String> photoUrlsList = new ArrayList<String>();
        photoUrlsList.add("http://animals/dog.jpg");
        List<TagsItem> tagsList = new ArrayList<TagsItem>();
        tagsList.add(new TagsItem(5, "Animals"));
        String status = "available";
        return given()
                .body(new Pet(id, category, name, photoUrlsList, tagsList, status))
                .when().post()
                .then().extract().response();
    }

    public Response addPetWithEmptyId(){
        return given()
                .body("{\n" +
                        "  \"id\": ,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"dogs\"\n" +
                        "  },\n" +
                        "  \"name\": \"The Hound of the Baskervilles\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"http://someurl.com\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"white color pet\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"sold\"\n" +
                        "}")
                .when().post()
                .then().extract().response();
    }

    public Response deletePet(int petId){
        return given()
                .basePath(basePath + "/" + petId)
                .when().delete();
    }


    public Response getPet(int petId) {
        return given()
                .basePath(basePath + "/" + petId)
                .when().get();
    }
}
