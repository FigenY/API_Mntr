package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class C01_01_JsonToJava {

    String kraftUrl="https://www.krafttechexlab.com";
    @Test
    public void t_allUsersToList() {
        /**CT D05 TC06
         * given accept type is JSON
         * And query param pagesize is 50
         * And query param page is 1
         * When user sends a get request to /allusers/alluser
         * Then status code 200
         * put all response body inside a list by as() method
         * make several verifications
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams("page", 1, "pagesize", 50)
                .when()
                .get(kraftUrl + "/sw/api/v1/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        // put all response body into  a list of map
        // convert jsonBody to Java Collection ==> De-serialization

        List<Map<String,Object>> allUsers=response.as(List.class);

        // ERROR !!!
        //Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.

        //verify that first users email is "afmercan@gmail.com"         [0].email[0]
                                // index
        String actualEmail= (String) allUsers.get(0).get("email");
        System.out.println("actualEmail = " + actualEmail);
        String expectedEmail="afmercan@gmail.com";
        Assert.assertEquals(actualEmail,expectedEmail);

        //verify that first users job is "Manual Tester"

        String actualJob= (String) allUsers.get(0).get("job");
        String expectedJob="Manual Tester";
        System.out.println("actualJob = " + actualJob);
        Assert.assertEquals(actualJob,expectedJob);

        //verify that first users second skill is JAVA

        List<String > skills= (List<String>) allUsers.get(0).get("skills");
        String actualSkill=skills.get(1);
        System.out.println("actualSkill = " + actualSkill);
        Assert.assertEquals(actualSkill,"Java");

        //verify that first id of education is 44

       List<Map<String,Object>> education= (List<Map<String, Object>>) allUsers.get(0).get("education");

       double actualId= (double) education.get(0).get("id");

       Assert.assertEquals(actualId,44);



    }
}
