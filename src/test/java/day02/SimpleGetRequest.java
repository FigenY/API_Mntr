package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String bookStoreBaseUrl="https://bookstore.toolsqa.com/BookStore/v1";

    @Test
    public void t_simpleGetRequestFirst() {

        RestAssured.get("https://bookstore.toolsqa.com/BookStore/v1/Books");
    }

    @Test
    public void t_basicGetRequest() {
        Response response = RestAssured.get(bookStoreBaseUrl+"/Books");

        // print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        // print body
        response.prettyPeek();
    }

    @Test
    public void t_GetRequestWithBody() {
        /**
         * given except type is json
         * when user sends a GET request
         * Then verify that status code is 200
         * And body is json format
         */


        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(bookStoreBaseUrl + "/Books");

        // verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify content type
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void t_getRequestandVerifywithRestAssured() {

        RestAssured
                .given()    // starts request
                .accept(ContentType.JSON)
                .when()
                .get(bookStoreBaseUrl + "/Books")
                .then() // --> starts validatable response
                .assertThat()
                .statusCode(200)
               // .and()
                .contentType("application/json; charset=utf-8");

    }

    @Test
    public void t_getRequest_withContainsMethod() {

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(bookStoreBaseUrl + "/Books");

        // verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify content type
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        //response validation method
        //first way
        Assert.assertTrue(response.prettyPrint().contains("O'Reilly Media"));

        //seconds way
        Assert.assertTrue(response.body().asString().contains("O'Reilly Media"));
    }
}
