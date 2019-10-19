package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class UserIdRequest {
    @NotNull
    private Long userId;

    public UserIdRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
