package api;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {

        baseURI = "https://jsonplaceholder.typicode.com";

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .build();
    }
}
