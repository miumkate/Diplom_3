package prepare;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class HttpClient {


    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    protected static RequestSpecification baseRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type","application/json")
                .addFilter(new ErrorLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected static ResponseSpecification baseResponseSpecification(){
        return new ResponseSpecBuilder()
                .build();
    }

    protected ValidatableResponse doGetRequest(String path, String bearerToken){
        return given()
                .spec(baseRequestSpecification())
                .auth().oauth2(bearerToken)
                .when()
                .get(path)
                .then()
                .spec(baseResponseSpecification());
    }

    protected ValidatableResponse doPostRequest(String path, Object body){
        return given()
                .spec(baseRequestSpecification())
                .body(body)
                .when()
                .post(path)
                .then()
                .spec(baseResponseSpecification());
    }

    protected ValidatableResponse doDeleteRequest(String path, String bearerToken){
        return given()
                .spec(baseRequestSpecification())
                .auth().oauth2(bearerToken)
                .when()
                .delete(path)
                .then()
                .spec(baseResponseSpecification());
    }
}
