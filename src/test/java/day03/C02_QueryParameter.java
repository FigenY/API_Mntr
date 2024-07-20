package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class C02_QueryParameter {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test

    public void t_queryParam() {
        /**
         * QUERY PARAMETER...FIRST WAY
         * Get specific user with QUERY PARAMETER
         * Send GET request to kraft
         * Content type is application/json
         * name: Thomas Eduson
         * skills: Cypress
         * pagesize: 50
         * page: 1
         * Query parameter would be given inside the query parameter method
         * Validate that status code is 200
         * Validate that content-type is application/json; charset=UTF-8
         * Validate that response body contains Developer
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams("page", 1)
                .queryParams("pagesize", 50)
                .queryParams("name", "Thomas Eduson")
                .queryParams("skills", "Cypress")
                .when()
                .get("allusers/alluser");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Developer"));


    }

    @Test
    public void t_queryParamWithMap() {
        /**
         * QUERY PARAMETER...SECOND WAY
         * Get specific user with QUERY PARAMETER
         * Send GET request to kraft
         * Content type is application/json
         * name: Thomas Eduson
         * skills: Cypress
         * pagesize: 50
         * page: 1
         * Query parameter would be given inside a map
         * Validate that status code is 200
         * Validate that content-type is application/json; charset=UTF-8
         * Validate that response body contains Developer
         */

        Map<String, Object> map=new HashMap<>();
        map.put("page",1);
        map.put("pagesize",50);
        map.put("name","Thomas Eduson");
        map.put("skills","Cypress");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams(map)
                .when()
                .get("allusers/alluser");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Developer"));
    }
}
