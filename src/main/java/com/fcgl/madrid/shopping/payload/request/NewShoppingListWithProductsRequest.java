package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NewShoppingListWithProductsRequest {
    @NotNull
    private Long userId;
    private String name;
    @NotNull
    private Boolean active;
    @NotNull
    private List<String> products;
    private static final String DEFAULT_NAME = "default";


    public NewShoppingListWithProductsRequest(Long userId, String name, Boolean active, List<String> products) {
        this.userId = userId;
        if (name == null || name.equals("")) {
            this.name = DEFAULT_NAME;
        } else {
            this.name = name;
        }
        this.active = active;
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
