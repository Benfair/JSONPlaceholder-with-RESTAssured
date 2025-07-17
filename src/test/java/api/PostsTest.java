package api;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("JSONPlaceholder API")
@Feature("Posts Endpoint")
public class PostsTest extends BaseTest {

    @Test
    @Story("Retrieve posts")
    @Description("Verify GET /posts returns all posts and validates the response")
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
    @Story("Retrieve posts")
    @Description("Verify GET /posts/{id} returns specific post with correct data")
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
    @Story("Create posts")
    @Description("Verify POST /posts creates new post successfully")
    public void createPost() {
        String newPost = """
        {
            "title": "Behind-the-Scenes",
            "body": "A quick peek at the magic, mess, and moments that make everything come together",
            "userId": 1
        }
        """;

        given()
            .spec(requestSpec)
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
            .body("id", notNullValue())
            .body("title", equalTo("Behind-the-Scenes"));
    }

    @Test
    @Story("Update posts")
    @Description("Verify PUT /posts/{id} updates existing post successfully")
    public void updatePost() {
        String update = """
        {
            "id": 1,
            "title": "Question of the Day",
            "body": "If you could solve one problem with technology, what would it be?",
            "userId": 1
        }
        """;

        given()
            .spec(requestSpec)
            .body(update)
        .when()
            .put("/posts/5")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
            .body("title", equalTo("Question of the Day"));
    }

    @Test
    @Story("Delete posts")
    @Description("Verify DELETE /posts/{id} removes post successfully")
    public void deletePost() {
        given()
            .spec(requestSpec)
        .when()
            .delete("/posts/3")
        .then()
            .statusCode(200);
    }
}