package com.fcgl.madrid.shopping.payload.request;

import javax.validation.constraints.NotNull;

public class GetAllUserShoppingListsRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;

    public GetAllUserShoppingListsRequest(Long userId, Integer page, Integer size) {
        this.userId = userId;
        this.page = page;
        this.size = size;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
