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

    // генерируем id в диапазоне от 1 до 1000
    Random random = new Random();
    int randomId = random.nextInt(1000 + 1);



    @Test
    public void PostCreatePet() {
        // генерируем id в диапазоне от 1 до 1000
        Random random = new Random();
        int randomId = random.nextInt(1000 + 1);

        PetData petData = new PetData();

        // создаём объект класса POJO.PetData для сериализации в json и заполняем поля


        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("url1");
        photoUrls.add("url2");
        petData.setId(randomId);
        petData.setCategory(1, "TestPet8");
        petData.setName("doggie");
        petData.setPhotoUrls(photoUrls);
        petData.setStatus_type(PetData.STATUS_TYPE.available);

        // создаем объект класса Gson и конвертируем наш POJO.OrderData в Json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(petData);
        System.out.println("RequestCreatePetPOST: \n" + jsonString);

        // делаем POST запрос на создание ордера
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


}
