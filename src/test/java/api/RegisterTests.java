package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("ReqRes API")
@Feature("User Registration")
public class RegisterTests {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST /register with valid data returns id and token")
    @Story("Successful registration")
    public void testRegisterSuccess() {
        String payload = """
            {
              "email": "eve.holt@reqres.in",
              "password": "pistol"
            }
            """;

        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/register")
                    .header("Content-Type", "application/json")
                    .body(payload)
                .when()
                    .post();

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", not(emptyString()));

        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST /register with missing password returns error")
    @Story("Failed registration")
    public void testRegisterFailure() {
        String payload = """
            {
              "email": "sydney@fife"
            }
            """;

        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/register")
                    .header("Content-Type", "application/json")
                    .body(payload)
                .when()
                    .post();

        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}