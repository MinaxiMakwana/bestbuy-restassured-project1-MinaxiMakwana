package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * 1. Verify the if the total is equal to 1561
 * 2. Verify the if the stores of limit is equal to 10
 * 3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
 * 4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
 * 5. Verify the storied=7 inside storeservices of the third store of second services
 * 6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
 * 7. Verify the state = MN of forth store
 * 8. Verify the store name = Rochester of 9th store
 * 9. Verify the storeId = 11 for the 6th store
 * 10. Verify the serviceId = 4 for the 7th store of forth service
 */


public class StoresAssertionTest extends TestBase {

    static ValidatableResponse response;

     String stores = PropertyReader.getInstance().getProperty("resourceStore");

    @BeforeClass
    public void getStores(){

        response = given()
                .when()
                .get(stores)
                .then().statusCode(200);
    }

   @Test
    public void verifyTheIfTheTotalIsEqualTo1561(){
        response.body("total", equalTo(1561));
    }
    @Test
    public void verifyTheIfTheStoresOfLimitIsEqualTo10(){
        response.body("limit", equalTo(10));
    }
    @Test
    public void checkTheSingleNameInverGroveHeightsInTheArrayList(){
        response.body("data.name", hasItem("Inver Grove Heights"));
    }
    @Test
    public void CheckTheMultipleNamesRosevilleBurnsvilleMaplewoodInTheArrayList(){
        response.body("data.name", hasItem("Roseville"))
                .body("data.name", hasItem("Burnsville"))
                .body("data.name", hasItem("Maplewood"));
    }
    @Test
    public void verifyTheStoried7InsideStoreservicesOfTheThirdStoreOfSecondServices(){
        response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }
    @Test
    public void checkHashMapValuesCreatedAtInsideStoreservicesMapWhereStoreNameRoseville(){
        response.body("data.find { it.name == 'Roseville' }.services[0].storeservices.map.createdAt",nullValue());
    }
    @Test
    public void verifyTheStateMNOfForthStore(){
        response.body("data[0].state", equalTo("MN"));
    }
    @Test
    public void verifyTheStoreNameRochesterOf9thStore(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    @Test
    public void verifyTheStoreId11ForThe6thStore(){
        response.body("data[5].id", equalTo(11));
    }
    @Test
    public void verifyTheServiceId4ForThe7thStoreOfForthService(){
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }
}
