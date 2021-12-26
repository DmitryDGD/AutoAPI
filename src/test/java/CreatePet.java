import PoJo.PetData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePet {



    final String baseUri = "https://petstore.swagger.io";

    // генерируем id пета в диапазоне от 1 до 1000
    Random random = new Random();
    int randomPetId = random.nextInt(1000 + 1);

    // создаём объект класса POJO.PetData для сериализации в json
    PetData petData = new PetData();


    @Test(priority = 1)
    public void PostCreatePet() {

        // заполняем класс PetData
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("url1");
        photoUrls.add("url2");

        petData.setId(randomPetId);
        petData.setCategory(1, "TestPet8");
        petData.setName("doggie");
        petData.setPhotoUrls(photoUrls);
        petData.setStatus_type(PetData.STATUS_TYPE.available);

        // создаем объект класса Gson и конвертируем наш POJO.PetData в Json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(petData);
        System.out.println("RequestCreatePetPOST: \n" + jsonString);

        // делаем POST запрос на создание пета
        Response responsePost = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when()
                .post("/v2/pet")
                .then()
                .extract().response();

        System.out.println("ResponseCreateOrderPOST: \n" + responsePost.asString());


        // делаем проверки
        assertEquals(responsePost.statusCode(), 200);
        assertEquals(responsePost.jsonPath().getString("id"), petData.getId().toString());
    }


    //проверяем созданного пета с помощью Get запроса
    @Test(priority = 2)
    public void GetPet() {

        final String getURL = "/v2/pet/" + randomPetId;

        Response responseGetPet = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when()
                .get(getURL)
                .then()
                .extract().response();

        assertEquals(responseGetPet.statusCode(), 200);
        assertEquals(responseGetPet.jsonPath().getString("id"), petData.getId().toString());
        System.out.println("ResponseGetPet: \n" + responseGetPet.asString());

    }

    public Integer getPetId() {
        return randomPetId;
    }

}
