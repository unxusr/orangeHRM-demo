import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    private static String orangehrmCookie;
    private static String sessionId;
    private String candidateId;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com";
        RestAssured.config = RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(true));
        loginAndGetToken();
    }

    public void loginAndGetToken() {
        // Get login page and get cookie
        Response initialResponse = given()
            .when()
            .get("/web/index.php/auth/login");
        orangehrmCookie = initialResponse.getCookie("orangehrm");

        // Extract _token from the initial response
        String token = initialResponse.htmlPath().getString("html.body.form.input._token");

        // Perform login
        String loginBody = "username=Admin&password=admin123&_token=" + token;
        Response validate = given()
            .header("Content-Type", "application/x-www-form-urlencoded")
            .cookie("orangehrm", orangehrmCookie)
            .body(loginBody)
            .when()
            .post("/web/index.php/auth/validate")
            .then()
            .statusCode(302)
            .extract().response();

        // Extract updated cookie
        orangehrmCookie = validate.getCookie("orangehrm");

        // Print the updated CSRF token
        System.out.println("Updated Cookie: " + orangehrmCookie);
    }

    @Test
    public void testGetCandidates() {
        System.out.println("Cookie2: " + orangehrmCookie);
        Response response = given()
            .header("Content-Type", "application/json")
            .cookie("orangehrm", orangehrmCookie)
            .queryParam("limit", 50)
            .queryParam("offset", 0)
            .queryParam("model", "list")
            .queryParam("sortField", "candidate.dateOfApplication")
            .queryParam("sortOrder", "DESC")
        .when()
            .get("/web/index.php/api/v2/recruitment/candidates")
        .then()
            .statusCode(401)
            .extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
    }
}