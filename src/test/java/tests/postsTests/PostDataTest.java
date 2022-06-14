package tests.postsTests;

import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.post.Post;
import utils.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.Specifications.POST_PATH;
import static utils.TextGenerator.randomString;

/**
 * Case 4
 */
public class PostDataTest {

    Post post;

    @Parameters({"titleParameter", "bodyParameter","userIdParameter" ,"idParameter","userId","id", "randomStringValue"})
    @Test
    public void postAdd( String titleParameter, String bodyParameter,String userIdParameter, String idParameter,
                         int userId, int id, int randomStringValue) {
        SoftAssert softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationCreate());
        post = new Post();
        post.setTitle(randomString(randomStringValue));
        post.setBody(randomString(randomStringValue));
        post.setUserId(userId);
        post.setId(id);

         Response response = given()
                .body(post)
                .when()
                .post(POST_PATH)
                .then().log().all()
                .body(containsString(idParameter))
                .extract().response();

        softAssert.assertEquals(response.jsonPath().getInt(userIdParameter), post.getUserId());
        softAssert.assertEquals(response.jsonPath().getString(titleParameter), post.getTitle());
        softAssert.assertEquals(response.jsonPath().getString(bodyParameter), post.getBody());
        softAssert.assertAll();
    }
}
