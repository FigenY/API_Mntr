package day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C02_JsonPath {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getUser_and_verify_with_jsonPath() {
        /*
            CT D04 TC01
            When user sends a GET request to /allusers/getbyid/{id}
            Given accept type is json
            And Path param user id is 111

            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
            And user's name should be Thomas Eduson
            And user's id should be 111
            And user's email should be thomas@test.com
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("allusers/getbyid/{id}");


        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        // user's name should be Thomas Eduson

        String name = response.path("name[0]");
        System.out.println(name);
        Assert.assertEquals("Thomas Eduson",name);

        //user's id should be 111
        int id=response.path("id[0]");
        System.out.println("id = " + id);
        Assert.assertEquals(id,111);

        //user's email should be thomas@test.com
        String email=response.path("email[0]");
        System.out.println("email = " + email);
        Assert.assertEquals(email,"thomas@test.com");



        // with jsonPath

        JsonPath jsonPath = response.jsonPath();

        // user's name should be Thomas Eduson
        String actualName=jsonPath.get("name[0]");
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(actualName,"Thomas Eduson");

        //user's id should be 111
        int actualId=jsonPath.get("id[0]");
        System.out.println("actualId = " + actualId);
        Assert.assertEquals(actualId,111);

        //user's email should be thomas@test.com
        String actualEmail=jsonPath.get("email[0]");
        System.out.println("actualEmail = " + actualEmail);
        Assert.assertEquals(actualEmail,"thomas@test.com");

    }
}
