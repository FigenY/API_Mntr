package day07;

import POJOTEMP.KraftRegisterUser;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C01_PostRequest {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": NAME,
    "email": EMAIL,
    "password": PASSWORD
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */
    @Test
    public void t_post_with_hardCode() {

        String name=new Faker().name().fullName();
        String email=new Faker().internet().emailAddress();
        String password=new Faker().internet().password();

        String jsonBody="{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "    }";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/allusers/register");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertNotNull(response.path("id"));

        //validate name
        String expectedName=name;
        System.out.println("name = " + name);
        String actualName=response.path("name");
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(actualName,expectedName);

        //validate email
        String expectedEmail=email;
        System.out.println("email = " + email);
        String actualEmail=response.path("email");
        System.out.println("actualEmail = " + actualEmail);
        Assert.assertEquals(actualEmail,expectedEmail);

    }

    @Test
    public void t_postWith_customJavaClass() {

        String name=new Faker().name().fullName();
        String email=new Faker().internet().emailAddress();
        String password=new Faker().internet().password();

        KraftRegisterUser kraftRegisterUser=new KraftRegisterUser(name,email,password);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .body(kraftRegisterUser)
                .when()
                .post("/allusers/register");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertNotNull(response.path("id"));

        //validate name
        String expectedName=name;
        System.out.println("name = " + name);
        String actualName=response.path("name");
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(actualName,expectedName);

        //validate email
        String expectedEmail=email;
        System.out.println("email = " + email);
        String actualEmail=response.path("email");
        System.out.println("actualEmail = " + actualEmail);
        Assert.assertEquals(actualEmail,expectedEmail);

    }
}
