package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

    @Test
    public void getAllUsers() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("email", everyItem(containsString("@")));
    }

    @Test
    public void getUserById() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("id", equalTo(1))
                .body("email", containsString("@"));
    }

    @Test
    public void deleteUser() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(200);
    }
}