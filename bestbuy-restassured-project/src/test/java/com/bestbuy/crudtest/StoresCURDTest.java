package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.sym.Name;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

public class StoresCURDTest extends TestBase {

    static String firstName = TestUtils.getRandomValue() + "Mina";
    static String lastName = TestUtils.getRandomValue() + "Makwana";
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "abc@gmail.com";

    static int store;


    @Test
    public void test001() {
        List<String> stores = new ArrayList<>();
        stores.add("Java");
        stores.add("Rest Assured");

        StorePojo storePojo = new StorePojo();
        storePojo.setId();
        storePojo.setName();
        storePojo.setType();
        storePojo.setAddress();
        storePojo.setCity();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body("StorePojo")
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

    }

    @Test
    public void test002() {
        String s1 = "findAll{it.Name == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response =
                given()
                        .when()
                        .get("/list")
                        .then().statusCode(200);
        HashMap<String, Object> studentMap = response.extract()
                .path(s1 + n + s2);
        response.body(s1 + n + s2, hasValue());
        Id = (int) studentMap.get("id");
    }

    @Test
    public void test003() {

    }

    @Test
    public void test004() {
        given()
                .pathParam("id")
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id" Id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}
