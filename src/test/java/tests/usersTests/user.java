package tests.usersTests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Specifications;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.Specifications.USER_PATH;

public class user {

    @Test
    public void validateWithJsonFromFile() throws IOException{

        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecificationOk());
        String requestId = "5";
        String expectedJsonResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/user.json")));
        String actualJsonResponse = RestAssured.given() //precondition is to give the base uri
                .when() //actual action is to perform the get request
                .get(USER_PATH)
                .asString();

        System.out.println(expectedJsonResponse);
    }
}
