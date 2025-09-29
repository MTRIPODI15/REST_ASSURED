package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("ReqRes API")
@Feature("Authentication")
public class AuthTests {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST /login with valid credentials returns token")
    @Story("Successful login")
    public void testLoginSuccess() {
        String payload = """
            {
              "email": "eve.holt@reqres.in",
              "password": "cityslicka"
            }
            """;

        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/login")
                    .header("Content-Type", "application/json")
                    .body(payload)
                .when()
                    .post();

        response.then()
                .statusCode(200)
                .body("token", not(emptyString()));

        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST /login with missing password returns error")
    @Story("Failed login")
    public void testLoginFailure() {
        String payload = """
            {
              "email": "eve.holt@reqres.in"
            }
            """;

        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/login")
                    .header("Content-Type", "application/json")
                    .body(payload)
                .when()
                    .post();

        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}