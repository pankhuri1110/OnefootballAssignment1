package com.bestbuy.api.automation.actions;

import com.bestbuy.api.automation.models.Request.PatchProductRequest;
import com.bestbuy.api.automation.models.Request.PostProductRequest;
import io.restassured.response.Response;

public interface ProductsActions {

    Response getAllProducts(int limit, int skip);

    Response createNewProduct(PostProductRequest postProductRequest);

    Response deleteProduct(Long id);

    Response getProductById(Long id);

    Response updateProductById(Long id, PatchProductRequest patchProductRequest);
}
