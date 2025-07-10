package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostsTest extends BaseTest {

    @Test
    public void getAllPosts() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("userId", everyItem(notNullValue()))
                .body("title", everyItem(not(emptyString())));
    }

    @Test
    public void getPostById() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("id", equalTo(1))
                .body("title", not(emptyString()));
    }

    @Test
    public void createPost() {
        String postJson = """
        {
            "title": "Behind-the-Scenes",
            "body": "A quick peek at the magic, mess, and moments that make everything come together",
            "userId": 1
        }
        """;

        given()
                .spec(requestSpec)
                .body(postJson)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("id", notNullValue())
                .body("title", equalTo("Behind-the-Scenes"));
    }

    @Test
    public void updatePost() {
        String updatedJson = """
        {
            "id": 1,
            "title": "Question of the Day",
            "body": "If you could solve one problem with technology, what would it be?",
            "userId": 1
        }
        """;

        given()
                .spec(requestSpec)
                .body(updatedJson)
                .when()
                .put("/posts/5")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("title", equalTo("Question of the Day"));
    }

    @Test
    public void deletePost() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/3")
                .then()
                .statusCode(200);
    }
}