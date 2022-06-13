package tests.postsTests;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Specifications;

import static io.restassured.RestAssured.given;
import static utils.Specifications.POST_PATH;

/**
 * Case 3
 */
public class GetResponseFromPostsWithIncorrectDataTest {

    @Parameters({"postNumber"})
    @Test
    public void getResponseFromPostsWithIncorrectData(String postIncorrectNumber) {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationError());

        Response response = given()
                .when()
                .get(POST_PATH + postIncorrectNumber)
                .then().log().all()
                .body(Matchers.anything())
                .extract().response();
    }
}
