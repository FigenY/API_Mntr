package day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class C01_HamCrestMatchers {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getOneUser() {

        /** CT D05 TC01
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code should be 200
         * And content type  should be application/json; charset=UTF-8
         */

         RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("allusers/getbyid/{id}")
                 .then()
                 .assertThat()
                 .statusCode(200)
                 .and()
                 .contentType("application/json; charset=UTF-8")
                 .and()
                        // key                  //value
                 .body("id[0]", equalTo(111),
                         "job[0]", equalTo("Developer"),
                         "name[0]",equalTo("Thomas Eduson"));


    }

    @Test
    public void t_verifyHeaderWithHamcrest() {
/**CT D05 TC03
 * given accept type is JSON
 * And path param id is 111
 * When user send a get request to /allusers/getbyid/{id}
 * Then status code 200
 * And content Type application/json; charset=UTF-8
 * And response header Content-Type should be application/json; charset=UTF-8
 * And response header Content-Length should be 606
 * And response header has a Date that should not be null
 * And json data should have email equal "thomas@test.com"
 * And json data should have company equal "GHAN Software"
 */

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("allusers/getbyid/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8")
                .and()

                // for verification header
                .header("Content-Type",equalTo("application/json; charset=UTF-8"))
                .and()
                .header("Content-Length",equalTo("606"))
                .header("Date",notNullValue())
                .body("email[0]",equalTo("thomas@test.com"),
                "company[0]",equalTo("GHAN Software"));


    }
}
