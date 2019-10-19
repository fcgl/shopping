package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class GetUserShoppingProductRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long shoppingListId;

    public GetUserShoppingProductRequest(Long userId, Long shoppingListId) {
        this.userId = userId;
        this.shoppingListId = shoppingListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
