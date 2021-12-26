import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteOrder {

    PlaceOrder placeOrder = new PlaceOrder();
    Integer orderId = placeOrder.getOrderId();

    final String baseUri = "https://petstore.swagger.io";
    final String getURL = "/v2/pet/" + orderId;

    @Test(priority = 1)
    public void deleteOrder() {

        // делаем запрос на удаление ордера
        Response responseDeleteOrder = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when()
                .delete(getURL)
                .then()
                .extract().response();

        // проверка кода ответа
        assertEquals(responseDeleteOrder.statusCode(), 200);
        System.out.println("BaseURI = " + baseUri);
        System.out.println("getURL = " + getURL);


        System.out.println(responseDeleteOrder.asString());

    }



    //проверяем что ордер удален с помощью Get запроса
    @Test(priority = 2)
    public void GetOrder() {



        Response responseGetOrder = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when()
                .get("getURL")
                .then()
                .extract().response();


        assertEquals(responseGetOrder.statusCode(), 404);

        System.out.println("ResponseGetPet: \n" + responseGetOrder.asString());

    }

}
