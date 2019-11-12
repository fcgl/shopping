package com.fcgl.madrid.shopping.controller;

import com.fcgl.madrid.shopping.dataModel.ShoppingProduct;
import com.fcgl.madrid.shopping.payload.request.GetUserShoppingProductRequest;
import com.fcgl.madrid.shopping.payload.request.NewShoppingProductRequest;
import com.fcgl.madrid.shopping.payload.request.ToggleProductCompletedRequest;
import com.fcgl.madrid.shopping.payload.request.UpdateNameRequest;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.service.ShoppingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping/product/v1")
public class ShoppingProductController {

    private ShoppingProductService shoppingProductService;

    @Autowired
    public void setCommentService(ShoppingProductService shoppingProductService) {
        this.shoppingProductService = shoppingProductService;
    }

    @PostMapping(path = "/new")
    public Response<ShoppingProduct> addShoppingList(@Valid @RequestBody NewShoppingProductRequest request) {
        return this.shoppingProductService.addShoppingProduct(request);
    }

    @GetMapping(path= "")
    public Response<List<ShoppingProduct>> getUserShoppingProduct(GetUserShoppingProductRequest request) {
        return this.shoppingProductService.getUserShoppingProduct(request);
    }

    @GetMapping(path="toggleCompleted")
    public Response<ShoppingProduct> toggleCompleted(ToggleProductCompletedRequest request) {
        return this.shoppingProductService.toggleCompleted(request);
    }

    @PostMapping(path = "/update/name")
    public Response<ShoppingProduct> updateName(UpdateNameRequest request) {
        return this.shoppingProductService.updateName(request);
    }

}
