package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewShoppingListRequest {
    @NotNull
    private Long userId;
    private String name;
    @NotNull
    private Boolean active;
    private static final String DEFAULT_NAME = "default";

    public NewShoppingListRequest(Long userId, String name, Boolean active) {
        this.userId = userId;
        if (name == null || name.equals("")) {
            this.name = DEFAULT_NAME;
        } else {
            this.name = name;
        }
        this.active = active;
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
}
