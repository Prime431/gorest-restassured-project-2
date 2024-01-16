package com.gorest.testsuit;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.gorest.testsuit.PostAssertionTest.response;
import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
}

    //1. Extract the All Ids
    @Test
    public void test001() {

        List<Integer> id = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all id is  : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Names are  : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 5th name is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of inactive status  : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of gender  : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

//6. Print the names of the object whose gender = female

    @Test
    public void test006() {
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of names of female gender  : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

//7. Get all the emails of the object where status = inactive

    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of inactive status emails  : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }

//8. Get the ids of the object where gender = male

    @Test
    public void test008() {
        List<String> ids = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of male gender ids  : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all status  : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

//10. Get email of the object where name = Dandapaani Agarwal

    @Test
    public void test0010() {
        String email = response.extract().path("[1].email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Dandapaani emial  : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5914152
    @Test
    public void test0011() {
        int id = response.extract().path("[3].id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id  : " + id);
        System.out.println("------------------End of Test---------------------------");
    }
}
