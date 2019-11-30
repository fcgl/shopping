package com.fcgl.madrid.shopping.payload.response;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.dataModel.ShoppingProduct;

import java.util.List;

public class ShoppingListProductResponse {

    private ShoppingList shoppingList;
    private List<ShoppingProduct> products;

    public ShoppingListProductResponse(ShoppingList shoppingList, List<ShoppingProduct> products) {
        this.shoppingList = shoppingList;
        this.products = products;
    }


    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ShoppingProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShoppingProduct> products) {
        this.products = products;
    }
}
