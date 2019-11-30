package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class GetUserShoppingListRequest {

    @NotNull
    private Long userId;
    private Boolean isActive;
    private Long shoppingListId;

    public GetUserShoppingListRequest(Long userId, Long shoppingListId, Boolean isActive) {
        this.userId = userId;
        this.shoppingListId = shoppingListId;
        this.isActive = isActive;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
