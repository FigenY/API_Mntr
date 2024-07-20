package day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C01_PathMethod {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_UserGetRequest_with_PathMethod() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("allusers/getbyid/{id}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.body().path(\"email\").toString() = " + response.body().path("email").toString());
        System.out.println("response.body().path(\"id\").toString() = " + response.body().path("id").toString());

        // with gpath syntax
        int id = response.path("id[0]");
        System.out.println("id = " + id);
        String name = response.path("name[0]");
        System.out.println("name = " + name);
        String email = response.path("email[0]");
        System.out.println("email = " + email);

    }

    @Test
    public void t_allUsers_with_pathMethod() {

        /*
            CT TC022
            Given accept type json
            And query  parameter value pagesize 50
            And query  parameter value page 1 // bir sayfada 50 kullanıcı getirecek
            When user sends GET request to /allusers/alluser
            Then response status code should be 200
            And response content-type application/json; charset=UTF-8
            Verify the first id is 1
            Verify the first name is MercanS
            Verify the last id is 102
            Verify the last name is GHAN
     */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams("page", 1)
                .queryParams("pagesize", 50)
                .when()
                .get("allusers/alluser");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //verify the first id is 1
       int firstId= response.path("id[0]");
        System.out.println("firstId = " + firstId);

        // get lastId
      //  int lastId= response.path("id[49]");
        int lastId= response.path("id[-1]");    //list.get(list.size-1)
        System.out.println("lastId = " + lastId);

        //Verify the last name is GHAN
        String lastName=response.path("name[-1]");
        System.out.println("lastName = " + lastName);

        Object skillsList = response.path("skills[0]");
        System.out.println("skillsList = " + skillsList);
        System.out.println("***************");
        // get MercanS 's second skill Java
        System.out.println("response.path(\"skills[0][1]\") = " + response.path("skills[0][1]"));
        System.out.println("***************");
        // get MercanS's  education info
        System.out.println("response.path(\"education[0]\") = " + response.path("education[0]"));
        System.out.println("***************");
        // get mercans 's first edu info
        System.out.println("response.path(\"education[0][0]\") = " + response.path("education[0][0]"));

        System.out.println("***************");
        //get first education's id
        System.out.println("response.path(\"education[0][0].id\") = " + response.path("education[0][0].id"));

       int eduId= response.path("education[0][0].id");
       Assert.assertEquals(44,eduId);


    }
}
