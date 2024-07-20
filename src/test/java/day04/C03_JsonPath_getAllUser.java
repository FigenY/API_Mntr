package day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class C03_JsonPath_getAllUser {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getAllUsers_and_verify_with_JsonPath(){
                  /*
            CT D04 TC02
            Given accept type is json
            When user sends a GET request to /allusers/alluser
            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams("page", 1)
                .queryParams("pagesize", 5)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath = response.jsonPath();

        //verify that first id is 1
        int firstId = jsonPath.get("id[0]");
        System.out.println("firstId = " + firstId);
        Assert.assertEquals(firstId,1);


        //verify that last id is 33

        int lastId=jsonPath.get("id[-1]");
        System.out.println("lastId = " + lastId);
        Assert.assertEquals(lastId,33);

        //verify ids are equals to 1 ,5 ,24, 29, 33

        System.out.println("jsonPath.getList(\"id\") = " + jsonPath.getList("id"));
        List<Integer> actualIdList = jsonPath.getList("id");

        // first way for expectedList
        List<Integer> expectedIdList=new ArrayList<>();
        expectedIdList.add(1);
        expectedIdList.add(5);
        expectedIdList.add(24);
        expectedIdList.add(29);
        expectedIdList.add(33);

        System.out.println("expectedIdList.get(0) = " + expectedIdList.get(0));
        System.out.println("actualIdList.get(0) = " + actualIdList.get(0));

        Assert.assertEquals(actualIdList,expectedIdList);

        // second way for expectedList
        List<Integer> expectedIdList2=new ArrayList<>(Arrays.asList(1,5,24,29,33));
        Assert.assertEquals(actualIdList,expectedIdList2);

        // verify that first  skill's first user        PHP

        //first way
        String actualFirstSkill=jsonPath.getString("skills[0][0]");
        System.out.println("actualFirstSkill = " + actualFirstSkill);
        Assert.assertEquals(actualFirstSkill,"PHP");

        //second way
        List<String> firstUserSkills= jsonPath.get("skills[0]");
        System.out.println("firstUserSkills = " + firstUserSkills);

        String fisrtUserSkillwithlist = firstUserSkills.get(0);
        Assert.assertEquals(fisrtUserSkillwithlist,"PHP");


        // VERİFY THAT first user's education school is "School or Bootcamp"
       Map<String,Object> map= jsonPath.get("education[0][0]");
        System.out.println("map.get(\"schoold\") = " + map.get("school"));

        String school1=jsonPath.getString("education[0][0].school");
        System.out.println("school1 = " + school1);
        String school2=jsonPath.getString("education[0].school[0]");
        System.out.println("school2 = " + school2);
        String school3=jsonPath.getString("[0].education[0].school");
        System.out.println("school3 = " + school3);

    }
}
