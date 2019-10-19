package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewShoppingProductRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long shoppingListId;
    @NotEmpty
    private String productName;
    private Float recommendedPrice;
    private Long recommendedStoreId;
    private Long recommendedProductId;

    public NewShoppingProductRequest(Long userId, Long shoppingListId, String productName,
                                     Float recommendedPrice, Long recommendedProductId,
                                     Long recommendedStoreId) {
        this.userId = userId;
        this.shoppingListId = shoppingListId;
        this.productName = productName;
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

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
