package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("ReqRes API")
@Feature("Error Handling")
public class ErrorTests {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /unknown/23 returns 404 for non-existent resource")
    @Story("Resource not found")
    public void testGetUnknownResourceNotFound() {
        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/unknown/23")
                .when()
                    .get();

        response.then()
                .statusCode(404);

        assertEquals("", response.getBody().asString().trim());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST /login with empty body returns 400")
    @Story("Missing credentials")
    public void testLoginWithEmptyBody() {
        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/login")
                    .header("Content-Type", "application/json")
                    .body("{}")
                .when()
                    .post();

        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }
}