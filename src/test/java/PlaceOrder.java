import PoJo.OrderData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
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

   // генерируем id в диапазоне от 1 до 1000
   Random random = new Random();
   int randomId = random.nextInt(1000 + 1);

   // сохраняем текущую дату и время в переменную в нужном формате
   Date dateNow = new Date();
   SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.S");
   String currentDate = formatForDateNow.format(dateNow);


   @Test
   public void PostCreateOrder() {

      // создаём объект класса POJO.OrderData для сериализации в json и заполняем поля
      OrderData orderData = new OrderData();
      orderData.setId(randomId);
      orderData.setPetId(1);
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
    //  assertEquals(responsePost.jsonPath().getInt("Id"), randomId);



   }


}
