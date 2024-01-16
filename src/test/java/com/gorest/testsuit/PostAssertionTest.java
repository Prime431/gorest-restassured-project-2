package com.gorest.testsuit;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

//    1. Verify the if the total record is 10

    @Test
    public void test011() {
        response.body(("size()"), equalTo(25));
    }

    //    2.Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.
    @Test
    public void test012() {
        response.body("[3].title", equalTo("Auctor corporis cupiditate abbas civis qui."));
    }

    //    3. Check the single user_id in the Array list (5914254)
    @Test
    public void test013() {
        response.body("[0].user_id", equalTo(5914254));
    }

    //    4. Check the multiple ids in the ArrayList (5914243, 5914206, 5914193)
    @Test
    public void test014() {
        response.body("findAll{it}.user_id", hasItems(5914243, 5914206, 5914193));
    }

//    5. Verify the body of userid = 5914254 is equal “Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."}
@Test
public void test015() {
    response.body("findAll{it.user_id == 5914254 }.body", hasItems("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
}

}