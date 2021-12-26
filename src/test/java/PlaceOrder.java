import PoJo.OrderData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PlaceOrder {

   final String baseUri = "https://petstore.swagger.io";

   // получаем ID созданного пета из CreatePet
   CreatePet createPet = new CreatePet();
   Integer petId = createPet.getPetId();

   // генерируем id ордера в диапазоне от 1 до 1000
   Random random = new Random();
   int randomOrderId = random.nextInt(1000 + 1);

   // сохраняем текущую дату и время в переменную в нужном формате
   Date dateNow = new Date();
   SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.S");
   String currentDate = formatForDateNow.format(dateNow);

   // создаём объект класса POJO.OrderData для сериализации в json
   OrderData orderData = new OrderData();


   @Test(priority = 1)
   public void PostCreateOrder() {

      // заполняем POJO.OrderData
      orderData.setId(randomOrderId);
      orderData.setPetId(petId);
      orderData.setQuantity(1);
      orderData.setShipDate(currentDate);
      orderData.setStatus_type(OrderData.STATUS_TYPE.placed);
      orderData.setCompleteType(true);

      // создаем объект класса Gson и конвертируем наш POJO.OrderData в Json
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonString = gson.toJson(orderData);
      System.out.println("RequestCreateOrderPOST: \n" + jsonString);


      // делаем POST запрос на создание ордера
      Response responsePost = given()
              .baseUri(baseUri)
              .contentType(ContentType.JSON)
              .body(jsonString)
              .when()
              .post("/v2/store/order")
              .then()
              .extract().response();

      System.out.println("ResponseCreateOrderPOST: \n" + responsePost.asString());

      // делаем проверки
      assertEquals(responsePost.statusCode(), 200);
      assertEquals(responsePost.jsonPath().getString("id"), orderData.getId().toString());
//      assertEquals(responsePost.jsonPath().getString("petId"), orderData.getPetId().toString());
   }

   //проверяем созданный ордер с помощью Get запроса
   @Test(priority = 2)
   public void GetOrder() {

      final String getURL = "/v2/store/order/" + randomOrderId;

      Response responseGetOrder = given()
              .baseUri(baseUri)
              .contentType(ContentType.JSON)
              .when()
              .get(getURL)
              .then()
              .extract().response();


      assertEquals(responseGetOrder.statusCode(), 200);

      System.out.println("ResponseGetOrder: \n" + responseGetOrder.asString());

   }

   public Integer getOrderId() {
      return randomOrderId;
   }


}
