import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class deleteOrder {

    final String baseUri = "https://petstore.swagger.io";

    @Test
    public void DeleteOrder() {

        // делаем GET запрос на удаление ордера
        Response responseGet = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when()
                .delete("v2/store/order/1")
                .then()
                .extract().response();

        // проверка кода ответа
        assertEquals(responseGet.statusCode(), 200);

        System.out.println(responseGet.asString());

    }

}
