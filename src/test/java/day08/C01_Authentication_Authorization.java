package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C01_Authentication_Authorization {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_login() {

        String email="leeroyjenkins@test.com";
        String password="1234.Asdf";

        Response response = RestAssured.given().accept(ContentType.MULTIPART)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("allusers/login");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token= response.path("token");
        System.out.println("token = " + token);


    }

    @Test
    public static String getToken() {

        String email="leeroyjenkins@test.com";
        String password="1234.Asdf";

        Response response = RestAssured.given().accept(ContentType.MULTIPART)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("allusers/login");



        String token= response.path("token");
        return token;

    }
}
