package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("ReqRes API")
@Feature("User Management")
public class UserTests {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users?page=2 returns valid user list")
    @Story("List users with pagination")
    public void testGetUsersPage2() {
        Response response = RestAssured
                .given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/users")
                    .queryParam("page", 2)
                .when()
                    .get();

        // Validaciones con Rest Assured
        response.then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", not(empty()))
                .body("data[0].email", containsString("@reqres.in"));

        // Validación extra con JUnit
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }
}