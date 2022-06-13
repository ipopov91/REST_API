package tests.usersTests;

import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Specifications;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static utils.Specifications.FILE_PATH;
import static utils.Specifications.USER_PATH;

/**
 * Case 6
 */
public class GetResponseFromUsersWithQueryParamTest {

    @Parameters({"idParameter", "idValue"})
    @Test
    public void getResponseFromUsersWithQueryParam(String idParameter, int idValue) throws IOException, JSONException {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());
        String expectedJsonResponse = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        Response response = given()
                .param(idParameter, idValue)
                .when()
                .get(USER_PATH)
                .then().log().all()
                .extract().response();

        JSONAssert.assertEquals(expectedJsonResponse, response.body().asString(), true);
    }
}
