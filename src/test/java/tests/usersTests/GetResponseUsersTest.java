package tests.usersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.user.Address;
import pageObjects.user.Company;
import pageObjects.user.Geo;
import pageObjects.user.User;
import utils.PropertyManager;
import utils.Specifications;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

import static utils.Specifications.USER_PATH;

/**
 * Case 5
 */
public class GetResponseUsersTest {

    public final static String USER_EXP_PATH_JSON = PropertyManager.getProperty("pathExpectedUser");
    public final static String USER_ACT_PATH_JSON = PropertyManager.getProperty("pathActualUser");

    @Parameters({"idValue","nameValue","userName","emailValue","streetValue","suiteValue","cityValue","zipcodeValue","latValue",
            "lngValue","phoneValue","websiteValue","companyNameValue","catchPhraseValue","bsValue"})
    @Test
    public void getResponseUsers(int idValue, String nameValue,String  userName,String emailValue,
                                 String streetValue, String suiteValue, String cityValue, String zipcodeValue, String latValue,
                                 String lngValue, String phoneValue, String websiteValue, String companyNameValue,
                                 String catchPhraseValue, String bsValue) throws IOException {

        SoftAssert softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());

        User userFive = new User(idValue,nameValue, userName, emailValue,
                new Address(streetValue, suiteValue, cityValue, zipcodeValue,
                        new Geo(latValue, lngValue)),phoneValue, websiteValue,
                new Company(companyNameValue, catchPhraseValue, bsValue));

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(USER_EXP_PATH_JSON), userFive);
        String jsonStringExpected = mapper.writeValueAsString(userFive);

        List<User> res = given()
                .when()
                .get(USER_PATH)
                .then().log().body()
                .extract().jsonPath().getList("", User.class);

        List<User> neededUser = res.stream().filter(x->x.getId().equals(idValue)).collect(Collectors.toList());

        mapper.writeValue(new File(USER_ACT_PATH_JSON), neededUser.get(0));
        String jsonStringActual = mapper.writeValueAsString(neededUser.get(0));
        softAssert.assertEquals(jsonStringExpected,jsonStringActual);
        softAssert.assertAll();
    }
}
