package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class ToggleProductCompletedRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long productId;

    public ToggleProductCompletedRequest(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
