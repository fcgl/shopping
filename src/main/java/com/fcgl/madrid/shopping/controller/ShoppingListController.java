package com.fcgl.madrid.shopping.controller;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.payload.request.GetAllUserShoppingListsRequest;
import com.fcgl.madrid.shopping.payload.request.NewShoppingListRequest;
import com.fcgl.madrid.shopping.payload.request.UserIdRequest;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping/list/v1")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public void setCommentService(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(path = "/new")
    public Response<ShoppingList> addShoppingList(@Valid @RequestBody NewShoppingListRequest request) {
        return this.shoppingListService.addShoppingList(request);
    }

    @GetMapping(path="/active")
    public Response<ShoppingList> getActiveUserShoppingList(UserIdRequest request) {
        return this.shoppingListService.getActiveUserShoppingList(request);
    }

    @GetMapping(path="/all")
    public Response<List<ShoppingList>> getAllUserShoppingLists(GetAllUserShoppingListsRequest request) {
        return this.shoppingListService.getAllUserShoppingLists(request);
    }

}
