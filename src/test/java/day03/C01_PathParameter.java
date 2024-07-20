package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C01_PathParameter {
String petStoreUrl="https://petstore.swagger.io/v2";
    @Test
    public void t_PathParameters01() {
        /**
         * PATH PARAMETER...FIRST WAY
         * Get specific book with PATH PARAMETER
         * Send GET request to petstore
         * Content type is application/json
         * Path parameter: 5
         * Path parameter would be given inside the GET method
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreUrl + "/pet/5");

        Assert.assertEquals(response.getContentType(),  "application/json");
        Assert.assertEquals(response.statusCode(),200);

        //verify that dog name is doggie
        Assert.assertTrue(response.body().asString().contains("doggie"));

        response.prettyPrint();
    }

    @Test
    public void t_withPathParam() {
/**
 * PATH PARAMETER...SECOND WAY
 * Get specific book with PATH PARAMETER
 * Send GET request to petstore
 * Content type is application/json
 * Path parameter: 5
 * Path parameter would be given inside the pathParam() method
 */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id", 5)
                .get(petStoreUrl + "/pet/{id}");    // endpoint içinde yer alan süslü parantez path param içinde de aynı olmalı

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("doggie"));

        response.prettyPrint();
    }

    @Test
    public void t_getPetByIdNegative() {
        /**
         * PATH PARAMETER...SECOND WAY
         * Get specific book with PATH PARAMETER
         * Send GET request to petstore
         * Content type is application/json
         * Path parameter: 2
         * Path parameter would be given inside the pathParam() method
         * status code must be 404
         * validate that response contains "Pet not found"
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id", 2)
                .get(petStoreUrl + "/pet/{id}");

        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Pet not found"));

        response.prettyPrint();
    }
}
