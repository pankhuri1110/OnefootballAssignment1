package com.bestbuy.api.automation.actionsImpl;

import com.bestbuy.api.automation.actions.ProductsActions;
import com.bestbuy.api.automation.models.Request.PatchProductRequest;
import com.bestbuy.api.automation.models.Request.PostProductRequest;
import com.bestbuy.api.automation.utilities.APIFunctions;
import io.restassured.response.Response;

public class ProductsActionsImpl implements ProductsActions {

    @Override
    public Response getAllProducts(int limit, int skip) {
        return APIFunctions.getRequest(limit, skip, "/products");
    }

    @Override
    public Response createNewProduct(PostProductRequest postProductRequest) {
        return APIFunctions.postRequest(postProductRequest, "/products");
    }

    @Override
    public Response deleteProduct(Long id) {
        return APIFunctions.deleteRequest(id, "/products");
    }

    @Override
    public Response getProductById(Long id) {
        return APIFunctions.getRequest(id, "/products");
    }

    @Override
    public Response updateProductById(Long id, PatchProductRequest patchProductRequest) {
        return APIFunctions.patchRequest(id, patchProductRequest, "/products");
    }
}
