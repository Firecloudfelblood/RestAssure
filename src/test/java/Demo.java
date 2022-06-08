
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    @Test
    public void testGetUser(){
        baseURI = "https://reqres.in/api";

        //puede ser usado sin extraer a string
        String body = given()
                .when()
                    .get("/users")
                .then()
                    .statusCode(200)
                    .body("data[1].first_name", equalTo("Janet"))
                // esta linea combierte el json de la respuesta a string
                    .extract().body().asString();

        System.out.println(body);
    }

    @Test
    public void testPostUser(){
        baseURI = "https://reqres.in/api";

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "Alejandra");
        map.put("job", "Customer Success");

        given()
                .log().all()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201);


    }
}
