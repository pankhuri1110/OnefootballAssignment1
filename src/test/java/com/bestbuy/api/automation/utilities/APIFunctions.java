package com.bestbuy.api.automation.utilities;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class APIFunctions {

    public static Response response;

    public static Response getRequest(int limit, int skip, String url) {
        response = SerenityRest.given()
                .param("$limit", limit)
                .param("$skip", skip)
                .when()
                .get(GenericFunctions.getConfigProperties().getProperty("baseURI").concat(url));
        return response;
    }

    public static Response getRequest(Long id, String url) {
        response = SerenityRest.given()
                .param("id", id)
                .when()
                .get(GenericFunctions.getConfigProperties().getProperty("baseURI").concat(url));
        return response;
    }

    public static <T> Response postRequest(T Object, String url) {
        response = SerenityRest.given().header("Content-Type","application/json")
                .when()
                .body(Object)
                .post(GenericFunctions.getConfigProperties().getProperty("baseURI").concat(url));
        return response;
    }

    public static <T> Response patchRequest(Long id, T Object, String url) {
        response = SerenityRest.given().header("Content-Type","application/json")
                .param("id", id)
                .when()
                .body(Object)
                .patch(GenericFunctions.getConfigProperties().getProperty("baseURI").concat(url));
        return response;
    }

    public static <T> Response deleteRequest(Long id, String url) {
        response = SerenityRest.given().header("Content-Type","application/json")
                .param("id", id)
                .when()
                .patch(GenericFunctions.getConfigProperties().getProperty("baseURI").concat(url));
        return response;
    }

}
