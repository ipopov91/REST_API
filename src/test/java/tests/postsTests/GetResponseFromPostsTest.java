package tests.postsTests;

import com.google.common.collect.Ordering;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static utils.Specifications.POST_PATH;

/**
 * Case 1
 */
public class GetResponseFromPostsTest {

    @Parameters({"idParameter"})
    @Test
    public void getResponseFromPosts(String idIParameter) {
        SoftAssert softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());

        Response response = given()
                .when()
                .get(POST_PATH)
                .then().log().all()
                .extract().response();
        List<String> jsonResponse = response.jsonPath().get(idIParameter);

        softAssert.assertTrue(Ordering.natural().isOrdered(jsonResponse));
        softAssert.assertAll();
    }
}
