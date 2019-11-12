package com.fcgl.madrid.shopping.dataModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long userId;
    @NotEmpty
    private String name;
    private Long createdDate;
    private Long updatedDate;
    private Boolean active;
    private Float totalPrice;
    private String summary;

    @OneToMany(mappedBy = "shoppingListId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ShoppingProduct> shoppingProducts;


    @PreUpdate
    public void preUpdate() {
        this.updatedDate = Instant.now().toEpochMilli();
    }

    public ShoppingList() {

    }

    public ShoppingList(Long userId, String name, Boolean active) {
        this.userId = userId;
        this.name = name;
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.active = active;
        this.totalPrice = (float) 0;
        this.summary = "";
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
