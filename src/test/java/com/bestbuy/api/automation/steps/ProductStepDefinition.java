package com.bestbuy.api.automation.steps;

import com.bestbuy.api.automation.actions.ProductsActions;
import com.bestbuy.api.automation.actionsImpl.ProductsActionsImpl;
import com.bestbuy.api.automation.models.Request.PatchProductRequest;
import com.bestbuy.api.automation.models.Request.PostProductRequest;
import com.bestbuy.api.automation.models.Response.ErrorResponse;
import com.bestbuy.api.automation.models.Response.GetProductsResponse;
import com.bestbuy.api.automation.models.Response.PostProductResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class ProductStepDefinition {

    PostProductRequest postProductRequest;
    ProductsActions productsActions;
    Response response;
    ErrorResponse errorResponse;
    PostProductResponse postProductResponse;
    GetProductsResponse getProductsResponse;
    private static Long id;
    public static String jsonAsString;


    @Given("User is having details {string}, {string}, {bigdecimal}, {bigdecimal}, {string}, {string}, {string}, {string}, {string}, {string} to create new product")
    public void user_Is_Having_Details_To_Create_NewProduct(String name, String type, BigDecimal price, BigDecimal shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        postProductRequest = PostProductRequest.builder()
                .name(name)
                .type(type)
                .price(price)
                .shipping(shipping)
                .upc(upc)
                .description(description)
                .manufacturer(manufacturer)
                .model(model)
                .url(url)
                .image(image).build();
    }

    @When("User create new product using API")
    public void user_Create_New_Product_Using_API() {
        productsActions = new ProductsActionsImpl();
        response = productsActions.createNewProduct(postProductRequest);
    }

    @Then("User should get {int} and validate response body fields with {string}, {string}, {int}, {string}, {string}")
    public void userShouldGetStatusCodeAndValidateResponseBodyFieldsWithErrorNameMessageCodeClassNameErrors(int statusCode, String errorName, String message, int code, String className, String errors) {
        errorResponse = response.getBody().as(ErrorResponse.class);
        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertEquals(errorName, errorResponse.getName());
        Assert.assertEquals(message, errorResponse.getMessage());
        Assert.assertEquals(code, errorResponse.getCode());
        Assert.assertEquals(className, errorResponse.getClassName());
        Assert.assertEquals(errors, errorResponse.getErrors().get(0));
    }

    @Then("User should get {int} and validate response body fields with {string}, {string}, {bigdecimal}, {bigdecimal}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userShouldGetStatusCodeAndValidateResponseBodyFieldsWithNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage(int getProductstatusCode, String name, String type, BigDecimal price, BigDecimal shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        Assert.assertEquals(getProductstatusCode, response.getStatusCode());
        postProductResponse = response.getBody().as(PostProductResponse.class);
        id = postProductResponse.getId();
        Assert.assertEquals(name, postProductResponse.getName());
        Assert.assertEquals(type, postProductResponse.getType());
        Assert.assertEquals(price, postProductResponse.getPrice());
        Assert.assertEquals(shipping, postProductResponse.getShipping());
        Assert.assertEquals(upc, postProductResponse.getUpc());
        Assert.assertEquals(description, postProductResponse.getDescription());
        Assert.assertEquals(manufacturer, postProductResponse.getManufacturer());
        Assert.assertEquals(model, postProductResponse.getModel());
        Assert.assertEquals(url, postProductResponse.getUrl());
        Assert.assertEquals(image, postProductResponse.getImage());
    }

    @When("User fetches product details for Id using Get API")
    public void user_Fetches_Product_Details_For_Id_Using_Get_API() {
        productsActions = new ProductsActionsImpl();
        response = productsActions.getProductById(id);
    }

    @Then("User should get {int} and validate response body fields with {long}, {int}, {int}, {string}, {string}, {bigdecimal}, {bigdecimal}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void user_Should_Get_Status_Code_And_Validate_Response_Body_Fields_With(int statusCode, Long total, int limit, int skip, String name, String type, BigDecimal price, BigDecimal shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        Assert.assertEquals(statusCode, response.getStatusCode());
        getProductsResponse = response.getBody().as(GetProductsResponse.class);
        Assert.assertEquals(total, getProductsResponse.getTotal());
        Assert.assertEquals(limit, getProductsResponse.getLimit());
        Assert.assertEquals(skip, getProductsResponse.getSkip());
        GetProductsResponse.ProductDetails productDetails = getProductsResponse.getData().get(0);
        Assert.assertEquals(id, productDetails.getId());
        Assert.assertEquals(name, productDetails.getName());
        Assert.assertEquals(type,productDetails.getType());
        Assert.assertEquals(price, productDetails.getPrice());
        Assert.assertEquals(upc, productDetails.getUpc());
        Assert.assertEquals(shipping, productDetails.getShipping());
        Assert.assertEquals(description, productDetails.getDescription());
        Assert.assertEquals(manufacturer, productDetails.getManufacturer());
        Assert.assertEquals(url, productDetails.getUrl());
        Assert.assertEquals(image, productDetails.getImage());
        Assert.assertEquals(productDetails.getCategories().size(), 0);
    }

    @When("User updates product details for Id and update {bigdecimal} using Patch API")
    public void user_Updates_Product_Details_For_Id_And_Update_Using_Patch_API(BigDecimal price) {
        PatchProductRequest patchProductRequest = PatchProductRequest.builder().price(price).build();
        response = productsActions.updateProductById(id, patchProductRequest);
    }


    @Then("User should get {int} and validate response body fields with {bigdecimal}")
    public void user_Should_Get_And_Validate_Response_Body_Fields_With(int getProductstatusCode, BigDecimal updatedPrice) {
        Assert.assertEquals(getProductstatusCode, response.getStatusCode());
        jsonAsString = response.asString();
        ArrayList<Map<String,?>> jsonAsArrayList = response.jsonPath().get();
        Assert.assertEquals(updatedPrice, new BigDecimal(String.valueOf(jsonAsArrayList.get(0).get("price"))));

    }

    @When("User delete product details for Id using Get API")
    public void user_Delete_Product_Details_For_Id_Using_Get_API() {
        productsActions = new ProductsActionsImpl();
        response = productsActions.deleteProduct(id);
    }

    @Then("User should get {int} and validate response is empty")
    public void user_Should_Get_And_Validate_Response_Is_Empty(int getProductDeleteStatusCode) {
        Assert.assertEquals(getProductDeleteStatusCode, response.getStatusCode());
        jsonAsString = response.asString();
        ArrayList<Map<String,?>> jsonAsArrayList = response.jsonPath().get();
        Assert.assertTrue(jsonAsArrayList.isEmpty());
    }

    @Given("User already deleted product and having Id of the product")
    public void user_Already_Deleted_Product_And_Having_Id_Of_The_Product() {

    }

    @Then("User should get {int} and validate response body fields with {long}, {int}, {int} and data list should be empty")
    public void user_Should_Get_And_Validate_Response_Body_Fields_And_Data_List_Should_Be_Empty(int getProductstatusCode, Long total, int limit, int skip) {
        Assert.assertEquals(getProductstatusCode, response.getStatusCode());
        getProductsResponse = response.getBody().as(GetProductsResponse.class);
        Assert.assertEquals(total, getProductsResponse.getTotal());
        Assert.assertEquals(limit, getProductsResponse.getLimit());
        Assert.assertEquals(skip, getProductsResponse.getSkip());
        Assert.assertTrue(getProductsResponse.getData().isEmpty());

    }
}
