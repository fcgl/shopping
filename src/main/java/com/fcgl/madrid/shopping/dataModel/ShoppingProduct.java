package com.fcgl.madrid.shopping.dataModel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.Instant;

@Entity
public class ShoppingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @NotNull
    @JoinColumn(name = "shoppingListId")
    private ShoppingList shoppingList;
    @NotEmpty
    private String productName;
    private Float recommendedPrice;
    private Long recommendedStoreId;
    private Long recommendedProductId;
    private Boolean completed;
    private Boolean recommendationComplete;

    public ShoppingProduct() {

    }

    public ShoppingProduct(Long userId, ShoppingList shoppingList, String productName) {
        this.userId = userId;
        this.shoppingList = shoppingList;
        this.productName = productName;
        this.recommendedPrice = null;
        this.recommendedProductId = null;
        this.recommendedStoreId = null;
        this.recommendationComplete = false;
        this.completed = false;
    }

    public ShoppingProduct(Long userId, ShoppingList shoppingList, String productName,
            Float recommendedPrice, Long recommendedStoreId, Long recommendedProductId) {
        this.userId = userId;
        this.shoppingList = shoppingList;
        this.productName = productName;
        this.recommendedPrice = recommendedPrice;
        this.recommendedProductId = recommendedProductId;
        this.recommendedStoreId = recommendedStoreId;
        this.recommendationComplete = recommendedPrice != null && recommendedProductId != null && recommendedStoreId != null;
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getRecommendationComplete() {
        return recommendationComplete;
    }

    public void setRecommendationComplete(Boolean recommendationComplete) {
        this.recommendationComplete = recommendationComplete;
    }
}
