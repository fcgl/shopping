package com.fcgl.madrid.shopping.payload.response;

public interface ShoppingListNoProduct {
    Long getUserId();
    String getName();
    Long getId();
    Long getUpdatedDate();
    Long getCreatedDate();
    Boolean getActive();
    Float getTotalPrice();
    String getSummary();
}
