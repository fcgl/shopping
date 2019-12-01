package com.fcgl.madrid.shopping.controller;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.payload.request.*;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.payload.response.ShoppingListNoProduct;
import com.fcgl.madrid.shopping.payload.response.ShoppingListProductResponse;
import com.fcgl.madrid.shopping.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping/list/v1")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public void setShoppingListService(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(path = "/new")
    public Response<ShoppingList> addShoppingList(@Valid @RequestBody NewShoppingListRequest request) {
        return this.shoppingListService.addShoppingList(request);
    }

    @GetMapping(path="/active")
    public ResponseEntity<Response<ShoppingList>> getActiveUserShoppingList(@Valid UserIdRequest request) {
        return this.shoppingListService.getActiveUserShoppingList(request);
    }

    @GetMapping(path="/all")
    public Response<List<ShoppingListNoProduct>> getAllUserShoppingLists(@Valid GetAllUserShoppingListsRequest request) {
        return this.shoppingListService.getAllUserShoppingLists(request);
    }

    @GetMapping(path="")
    public ResponseEntity<Response<ShoppingList>> getUserShoppingLost(@Valid GetUserShoppingListRequest request) {
        if (request.getActive()) {
            return this.shoppingListService.getActiveUserShoppingList(new UserIdRequest(request.getUserId()));
        } else {
            return this.shoppingListService.getUserShoppingList(request);
        }
    }

    @PostMapping(path="/products")
    public ResponseEntity<Response<ShoppingListProductResponse>> addShoppingListAndProducts(@Valid @RequestBody NewShoppingListWithProductsRequest request) {
        return this.shoppingListService.addShoppingListAndProducts(request);
    }

}
