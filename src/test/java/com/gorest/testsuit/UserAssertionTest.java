package com.gorest.testsuit;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.gorest.testsuit.PostAssertionTest.response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest extends TestBase {
static ValidatableResponse response;
    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }

    //    1. Verify the if the total record is 20
    @Test
    public void test011() {
        response.body(("size()"), equalTo(20));
    }

    //2. Verify the if the name of id = 5914155 is equal to ”Manikya Asan MD”
    @Test
    public void test002() {

        response.body("[0].name", equalTo("Manikya Asan MD"));
    }

    //3. Check the single ‘Name’ in the Array list (Prof. Chakrika Embranthiri)
    @Test
    public void test003() {

        response.body("[1].name", equalTo("Prof. Chakrika Embranthiri"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dandapaani Agarwal, Bilwa Embranthiri, Chandini Prajapat )
    @Test
    public void test004() {

        response.body("[1].name", equalTo("Prof. Chakrika Embranthiri"))
                .body("[4].name", equalTo("Bilwa Embranthiri"))
                .body("[5].name", equalTo("Chandini Prajapat"));
    }

    //5. Verify the email of userid = 5914151 is equal “somu_pillai@davis.example”
    @Test
    public void test005() {

        response.body("[4].email", equalTo("bilwa_embranthiri@kuvalis.example"));
    }

//6. Verify the status is “Active” of user name is “Somu Pillai”

    @Test
    public void test006() {

        response.body("[4].status", equalTo("inactive"));
    }

    //7. Verify the Gender = female of user name is “Bilwa Embranthiri”
    @Test
    public void test007() {

        response.body("[15].gender", equalTo("female"));
    }

}
