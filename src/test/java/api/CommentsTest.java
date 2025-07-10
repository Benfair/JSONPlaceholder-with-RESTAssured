package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CommentsTest extends BaseTest {

    @Test
    public void getAllComments() {
        given()
                .spec(requestSpec)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schemas/comment-schema.json"))
                .body("email", everyItem(containsString("@")));
    }

    @Test
    public void getCommentsForPost() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1/comments")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schemas/comment-schema.json"))
                .body("[0].postId", equalTo(1));
    }

    @Test
    public void createComment() {
        String commentJson = """
        {
            "postId": 1,
            "name": "Sandy Moore",
            "email": "sandymoore@company.com",
            "body": " I really enjoyed reading this post! The insights were clear and very helpful. Looking forward to more content like this."
        }
        """;

        given()
                .spec(requestSpec)
                .body(commentJson)
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/comment-schema.json"))
                .body("id", notNullValue())
                .body("postId", equalTo(1));
    }
}