package tests.usersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.user.Address;
import pageObjects.user.Company;
import pageObjects.user.Geo;
import pageObjects.user.User;
import utils.Specifications;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

import static utils.Specifications.USER_PATH;

/**
 * Case 5
 */
public class GetResponseUsersTest {

    @Test(dataProvider = "parameterTestProvider")
    public void getResponseUsers(User user, int idValue) throws IOException {

        SoftAssert softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStringExpected = mapper.writeValueAsString(user);

        List<User> res = given()
                .when()
                .get(USER_PATH)
                .then().log().body()
                .extract().jsonPath().getList("", User.class);

        List<User> neededUser = res.stream().filter(x->x.getId().equals(idValue)).collect(Collectors.toList());
        String jsonStringActual = mapper.writeValueAsString(neededUser.get(0));

        softAssert.assertEquals(jsonStringActual,jsonStringExpected);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] parameterTestProvider() {

        User userFive = new User(5,"Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
                new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263",
                        new Geo("-31.8129", "62.5342")),"(254)954-1289", "demarco.info",
                new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems"));

        return new Object[][]{
                {userFive, 5}
        };
    }
}
