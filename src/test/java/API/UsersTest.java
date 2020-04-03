package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertTrue;

public class UsersTest {

    @Test
    public void testGetUsers(){

        // Отправка запроса и получение ответа
        JsonPath response = RestAssured.given()
                .baseUri("https://reqres.in/")
                .get("api/users?page=2")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        Object b = response.getJsonObject("");

        // Подготовка данных ответа к перебору
        LinkedHashMap response_map = response.get();
        Set response_set = response_map.entrySet();

        // Получение списка пользователей
        ArrayList users_map_list = new ArrayList();
        for (Object element: response_set){
            Map.Entry mapEntry = (Map.Entry) element;
            if (mapEntry.getKey().equals("data")){
                users_map_list = (ArrayList) mapEntry.getValue();
            }
        }

        // Перебор всех пользователей и проверка на null
        for (int i=0; i<users_map_list.size(); i++) {
            LinkedHashMap user_map = (LinkedHashMap) users_map_list.get(i);

            // маппинг данных в обьект User
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.convertValue(user_map, User.class);

            // Проверка параметров пользователя на null
            assertTrue(user.getId() != null,
                    "У пользователя пустое поле id");
            assertTrue(user.getAvatar() != null,
                    "У пользователя пустое поле avatar");
            assertTrue(user.getLastName() != null,
                    "У пользователя пустое поле last_name");
            assertTrue(user.getFirstName() != null,
                    "У пользователя пустое поле first_name");
            assertTrue(user.getEmail() != null,
                    "У пользователя пустое поле email");

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

        // маппинг данных в обьект User
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(response.get(), User.class);

        // Проверка на соответствие полученных данных с отправленными данными
        assertTrue(user.getName().equals(newUser.get("name").getAsString()),
                "Полученное имя не соответствует отправленному имени");
        assertTrue(user.getJob().equals(newUser.get("job").getAsString()),
                "Полученная работа не соответствует отправленной работе");

    }
}
