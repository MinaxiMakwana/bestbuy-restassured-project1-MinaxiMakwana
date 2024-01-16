package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("products")
                .then().statusCode(200);
    }

    //Extract the limit
    @Test
    public void test21() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the total
    @Test
    public void test22() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th product
    @Test
    public void test23() {
        String Duracell = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("5th product is : " + Duracell);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract the names of all the products
    @Test
    public void test24() {
        List<String> listOfNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of names are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract the productId of all the products
    @Test
    public void test25() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of productIds are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //Print the size of the data list
    @Test
    public void test26() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test27() {
        List<HashMap<String, ?>> productsValues = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List all the value of the products : " + productsValues);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test28() {
        String productName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the categories of 8th products
    @Test
    public void test29() {
        List<Integer> listOfcategories = response.extract().path("data[8].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories of all the 8th product is : " + listOfcategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get categories of the store where product id = 150115
    @Test
    public void test30() {
        List<Integer> listOfIds = response.extract().path("data[3].id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product Id is : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the descriptions of all the products
    @Test
    public void test31() {
        List<Integer> listOfDec = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of descriptions are : " + listOfDec);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get id of all the all categories of all the products
    @Test
    public void test32() {
        List<Integer> listOfCategoriesIDs = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all categories are : " + listOfCategoriesIDs);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the product names Where type = HardGood
    @Test
    public void test33() {
        List<String> namesOfTheProducts = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of names are : " + namesOfTheProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test34() {
        List<String> namesOfTheCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.Categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Categories names are : " + namesOfTheCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all products whose price < 5.49
    @Test
    public void test35() {
        String lessThan = response.extract().path("data.findAll{it.price<5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product which is less then 5.49 is : " + lessThan);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test36() {
        List<String> namesOfTheCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.Categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Categories names are : " + namesOfTheCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the manufacturer of all the products
    @Test
    public void test37() {
        List<String> manufacturer = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all manufacturer names are : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");

    }

    //Find the image of products whose manufacturer is = Energizer
    @Test
    public void test38() {
        List<String> manufacturerImage = response.extract().path("data.findAll{it.image == 'http://img.bbystatic.com/BestBuy_US/images/products/3331/333179_sa.jpg'}.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Image of Energizer manufacturer is : " + manufacturerImage);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test39() {
        String moreThan = response.extract().path("data.findAll{it.price>5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product which is more then 5.49 is : " + moreThan);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the uri of all the products
    @Test
    public void test40() {
        List<Integer> UriOfAllTheStore = response.extract().path("data.uri");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the stores Lat : " + UriOfAllTheStore);
        System.out.println("------------------End of Test---------------------------");
    }

}
