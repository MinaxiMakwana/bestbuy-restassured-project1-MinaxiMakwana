package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * 1. Extract the limit
 * 2. Extract the total
 * 3. Extract the name of 5th store
 * 4. Extract the names of all the store
 * 5. Extract the storeId of all the store
 * 6. Print the size of the data list
 * 7. Get all the value of the store where store name = St Cloud
 * 8. Get the address of the store where store name = Rochester
 * 9. Get all the services of 8th store
 * 10. Get storeservices of the store where service name = Windows Store
 * 11. Get all the storeId of all the store
 * 12. Get id of all the store
 * 13. Find the store names Where state = ND
 * 14. Find the Total number of services for the store where store name = Rochester
 * 15. Find the createdAt for all services whose name = “Windows Store”
 * 16. Find the name of all services Where store name = “Fargo”
 * 17. Find the zip of all the store
 * 18. Find the zip of store name = Roseville
 * 19. Find the storeservices details of the service name = Magnolia Home Theater
 * 20. Find the lat of all the stores
 */

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
    }
    @Test
    public void extractTheLimit() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractTheTotal() {

      int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractTheNameOf5tStore() {

        String maplewood = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("5th store is : " + maplewood);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractTheNamesOfAllTheStore() {

        List<String> listOfNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractTheStoreIdOfAllTheStore() {

        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test //need to check
    public void printTheSizeOfTheDataList() {

        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllTheValueOfTheStoreWhereStoreNameIsStCloud() {

        List<HashMap<String, ?>> StCloudValues = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List all the value of St Cloud store : " + StCloudValues);
        System.out.println("------------------End of Test---------------------------");
    }


    @Test
    public void getTheAddressOfTheStoreWhereStoreNameIsRochester() {

        String rochesterAddress = response.extract().path("data[8].address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Rochester store Address : " + rochesterAddress);
        System.out.println("------------------End of Test---------------------------");
    }


    @Test
    public void getAllTheServicesOf8thStore() {

        List<Integer> listOfServices = response.extract().path("data[8].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the services of Rochester : " + listOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getStoreServicesOfTheStoreWhereServiceNameWindowsStore() {

        List<HashMap<String, ?>> WindowsStore = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Windows Store'}.collect { it.storeservices}}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of the services of Windows Store : " + WindowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllTheStoreIdOfAllTheStore() {

        List<Integer> listOfStoreId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the storeId of all the store : " + listOfStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getIdOfAllTheStore() {

        List<Integer> listOfStoreIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the storeIds : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheStoreNamesWhereStateND() {

        List<HashMap<String, ?>> StateIsND = response.extract().path("data.findAll{it.name == 'ND'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name - state is ND : " + StateIsND);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheTotalNumberOfServicesForTheStoreWhereStoreNameIsRochester() {

        List<HashMap<String, ?>> servicesForRochester = response.extract().path("data.findAll{it.name == 'Rochester'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for the Rochester : " + servicesForRochester);
        System.out.println("------------------End of Test---------------------------");
    }


    @Test
    public void findTheCreatedAtForAllServicesWhoseNameWindowsStore() {

        List<HashMap<String, ?>> noOfWindowsStoreServices = response.extract().path("data.services.findAll{ it.name == 'Windows Store' }.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for the Windows Store : " + noOfWindowsStoreServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test       //need to check
    public void findTheNameOfAllServicesWhereStoreNameIsFargo() {

        List<String> allTheServicesForFargo = response.extract().path("data.services*.findAll{it.name == 'Fargo'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for the Fargo : " + allTheServicesForFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheZipOfAllTheStore() {

        List<Integer> zipOfAllTheStore = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the stores zip code : " + zipOfAllTheStore);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void findTheZipOfRoseville() {

        List<Integer> zipOfRoseville = response.extract().path("data[2].Zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Roseville zip : " + zipOfRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findThestoreservicesDetailsOfTheServiceNameIsMagnoliaHomeTheater() {

        List<String> detailOfMagnoliaHomeTheater = response.extract().path("data.service.findAll{it.name == 'MagnoliaHomeTheater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Details of Magnolia Home Theater : " + detailOfMagnoliaHomeTheater);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheLatOfAllTheStores() {

        List<Integer> latOfAllTheStore = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the stores Lat : " + latOfAllTheStore);
        System.out.println("------------------End of Test---------------------------");
    }

}
