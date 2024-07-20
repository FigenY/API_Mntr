package day06;

import POJOTEMP.PetStore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_Pojo_Petstore {
private String perstoreUrl="https://petstore.swagger.io/v2";
    @Test
    public void test() {
        /**
         * CONVERT JSON OBJECT TO POJO (Plain Old Java Object - Custom Class)
         * WITH AS() METHOD (DE-SERILIZIATION)
         * Send GET request to petstore
         * Content type is Content-Type, api_key, Authorization
         * Path parameter: 5
         * Validate that name is "doggie"
         * Validate that id is "5"
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                 .pathParam("id", 5)
                .when()
                .get(perstoreUrl+"/pet/{id}");

        Assert.assertEquals(response.statusCode(),200);

        PetStore petStore=response.as(PetStore.class);

        System.out.println("petStore.getId() = " + petStore.getId());
        System.out.println("petStore.getName() = " + petStore.getName());
        System.out.println("petStore.getStatus() = " + petStore.getStatus());

        Assert.assertEquals(petStore.getId(),5);
        Assert.assertEquals(petStore.getName(),"doggie");
        Assert.assertEquals(petStore.getStatus(),"string");


    }

}


