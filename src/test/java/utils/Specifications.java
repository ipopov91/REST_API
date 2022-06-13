package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    private final static String URL = PropertyManager.getProperty("page");
    private final static String STATUS_CODE_OK = PropertyManager.getProperty("statusCodeOk");
    private final static String STATUS_CODE_ERROR = PropertyManager.getProperty("statusCodeError");
    private final static String STATUS_CODE_CREATE = PropertyManager.getProperty("statusCodeCreate");
    public final static String POST_PATH = PropertyManager.getProperty("postPath");
    public final static String USER_PATH = PropertyManager.getProperty("userPath");
    public final static String FILE_PATH = PropertyManager.getProperty("pathToJsonFile");

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationOk() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(STATUS_CODE_OK))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationError() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(STATUS_CODE_ERROR))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationCreate() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(STATUS_CODE_CREATE))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
