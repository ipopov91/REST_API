package tests.usersTests;

import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import org.testng.Assert;
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
        String aw = "[\n" +
                "  {\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"Chelsey Dietrich\",\n" +
                "    \"username\": \"Kamren\",\n" +
                "    \"email\": \"Lucio_Hettinger@annie.ca\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Skiles Walks\",\n" +
                "      \"suite\": \"Suite 351\",\n" +
                "      \"city\": \"Roscoeview\",\n" +
                "      \"zipcode\": \"33263\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-31.8129\",\n" +
                "        \"lng\": \"62.5342\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"(254)954-1289\",\n" +
                "    \"website\": \"demarco.info\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Keebler LLC\",\n" +
                "      \"catchPhrase\": \"User-centric fault-tolerant solution\",\n" +
                "      \"bs\": \"revolutionize end-to-end systems\"\n" +
                "    }\n" +
                "  }\n" +
                "]";

        Response response = given()
                .param(idParameter, idValue)
                .when()
                .get(USER_PATH)
                .then().log().all()
                .extract().response();

        Assert.assertEquals(aw, response.body().asString());
        JSONAssert.assertEquals(expectedJsonResponse, response.body().asString(), true);
    }
}
