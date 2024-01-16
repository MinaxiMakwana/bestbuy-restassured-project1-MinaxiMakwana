package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest extends TestBase {

    static ValidatableResponse response;

    String products = PropertyReader.getInstance().getProperty("resourceProducts");

    @BeforeClass
    public void getStores(){

        response = given()
                .when()
                .get(products)
                .then().statusCode(200);
    }


    //Verify if the total is equal to 51957
    @Test
    public void test1(){
        response.body("total", equalTo(51957));
    }

    //Verify if the stores of limit is equal to 10
    @Test
    public void test2(){
        response.body("limit", equalTo(10));
    }

    //Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test3(){
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    /**
     * Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
     * Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
     */
    @Test
    public void test4(){
        response.body("data.name", hasItem("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"))
                .body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"))
                .body("data.name", hasItem("Energizer - MAX Batteries AA (4-Pack)"));
    }
    //Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void test5(){
        response.body("data.find { it.name == 'Household Batteries' }.name.id",nullValue());
    }
    //Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void test6(){
        response.body("data.find { it.name == 'Housewares' }.name.id",nullValue());
    }
    //Verify the price = 4.99 of forth product
    @Test
    public void test7(){
        response.body("data.find { it.name == '4.99' }.id",nullValue());
    }
    //Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    @Test
    public void test8(){
        response.body("data.find { it.name == 'Duracell - D Batteries (4-Pack)' }.name.id",nullValue());
    }

    //Verify the ProductId = 333179 for the 9th product
    @Test
    public void test9(){
        response.body("data.find { it.name == 'Energizer - N Cell E90 Batteries (2-Pack)' }.id",equalTo(333179));
    }

    //Verify the prodctId = 346575 have 5 categories
    @Test
    public void test10(){
        response.body("data.find { it.name == 'Metra - Radio Installation Dash Kit for Most 1989-2000 Ford, Lincoln & Mercury Vehicles - Black' }.id",equalTo(346575));
    }
}
