package tests.postsTests;

import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Specifications;

import static io.restassured.RestAssured.given;
import static utils.Specifications.POST_PATH;

/**
 * Case 2
 */
public class GetResponseFromPostsWithQueryParamTest {

    @Parameters({"postNumber", "titleParameter", "bodyParameter", "idParameter","userIdParameter", "idValue",
                "userIdValue"})
    @Test
    public void getResponseFromPostsWithQueryParam(String postNumber, String titleParameter, String bodyParameter,
                                                   String idParameter, String userIdParameter, String idValue,
                                                   String userIdValue) {
        SoftAssert softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());

        Response response = given()
                .when()
                .get(POST_PATH + postNumber)
                .then().log().all()
                .extract().response();

        softAssert.assertFalse(response.jsonPath().getString(titleParameter).isEmpty());
        softAssert.assertFalse(response.jsonPath().getString(bodyParameter).isEmpty());
        softAssert.assertEquals(idValue, response.jsonPath().getString(idParameter));
        softAssert.assertEquals(userIdValue, response.jsonPath().getString(userIdParameter));
        softAssert.assertAll();
    }
}
