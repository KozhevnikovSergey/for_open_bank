package API;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertTrue;

public class UsersTest {

    @Test
    public void testGetUsers() {

        // Отправка запроса и получение ответа
        JsonPath response = RestAssured.given()
                .baseUri("https://reqres.in/")
                .get("api/users?page=2")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        // Подготовка данных ответа к перебору
        LinkedHashMap response_map = response.get();
        Set response_set = response_map.entrySet();

        // Получение списка пользователей
        ArrayList users_list = new ArrayList();
        for (Object element: response_set){
            Map.Entry mapEntry = (Map.Entry) element;
            if (mapEntry.getKey().equals("data")){
                users_list = (ArrayList) mapEntry.getValue();
            }
        }

        // Проход по всем пользователям
        for (int i=0; i<users_list.size(); i++) {
            // Подготовка пользователя к перебору параметров
            LinkedHashMap user = (LinkedHashMap) users_list.get(i);
            Set user_set = user.entrySet();

            // Перебор параметров пользователя для проверки на null
            for (Object param: user_set){
                Map.Entry mapEntry =  (Map.Entry) param;
                assertTrue(mapEntry.getValue() != null,
                        "У пользователя есть пустое поле");
            }
        }

    }

    @Test
    public void testCreateUser() {

        // Подготовка json для запроса
        JsonObject newUser = new JsonObject();
        newUser.addProperty("name", "morpheus");
        newUser.addProperty("job", "leader");

        // Отправка запроса на создание пользователя и получение ответа на запрос
        JsonPath response = RestAssured.given()
                .baseUri("https://reqres.in/")
                .basePath("api/users")
                .contentType(ContentType.JSON)
                .body(newUser.toString())
                .when()
                .post()
                .then().statusCode(201)
                .extract().jsonPath();

        // Проверка на соответствие полученных данных с отправленными данными
        assertTrue(response.get("name").equals(newUser.get("name").getAsString()),
                "Полученное имя не соответствует отправленному имени");
        assertTrue(response.get("job").equals(newUser.get("job").getAsString()),
                "Полученная работа не соответствует отправленной работе");

    }
}
