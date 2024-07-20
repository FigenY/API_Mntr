package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C03_ResponseHeaders {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_headersTest() {
/**
 * HEADERS VALIDATION
 * Send GET request to kraftexlab
 * Get all information of a specific user whose id is 111
 * Validate that status code is 200
 * Validate that content-length is 606
 * Validate that content-type is "application/json; charset=UTF-8"
 * Validate that response headers have "Date"
 */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);

        // for header verification

        // Validate that content-length is 606
        Assert.assertEquals(response.header("content-length"),"606");

        //Validate that content-type is "application/json; charset=UTF-8"
        Assert.assertEquals(response.header("content-type"),"application/json; charset=UTF-8");

        //* Validate that response headers have "Date"
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
