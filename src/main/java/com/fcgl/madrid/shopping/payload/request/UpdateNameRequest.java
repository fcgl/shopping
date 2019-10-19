package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class UpdateNameRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long shoppingProductId;
    @NotNull
    private String name;
    private Float recommendedPrice;
    private Long recommendedStoreId;
    private Long recommendedProductId;

    public UpdateNameRequest(Long userId, Long shoppingProductId, String name, Float recommendedPrice, Long recommendedStoreId, Long recommendedProductId) {
        this.userId = userId;
        this.shoppingProductId = shoppingProductId;
        this.name = name;
        this.recommendedPrice = recommendedPrice;
        this.recommendedStoreId = recommendedStoreId;
        this.recommendedProductId = recommendedProductId;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShoppingProductId() {
        return shoppingProductId;
    }

    public void setShoppingProductId(Long shoppingProductId) {
        this.shoppingProductId = shoppingProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Float recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public Long getRecommendedStoreId() {
        return recommendedStoreId;
    }

    public void setRecommendedStoreId(Long recommendedStoreId) {
        this.recommendedStoreId = recommendedStoreId;
    }

    public Long getRecommendedProductId() {
        return recommendedProductId;
    }

    public void setRecommendedProductId(Long recommendedProductId) {
        this.recommendedProductId = recommendedProductId;
    }
}
