import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebShopTests {
    @BeforeEach
    void beforeEach() {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
    }

    @Test
    void subscribeButtonTest() {
        String data = "email=testuser@testdomain.com";

        NewsletterReponse response = given()
                .contentType("application/x-www-form-urlencoded")
                .body(data)
                .when()
                .post("/subscribenewsletter")
                .then()
                .statusCode(200)
                .extract()
                .as(NewsletterReponse.class);

        assertTrue(response.isSuccess());
    }
}
