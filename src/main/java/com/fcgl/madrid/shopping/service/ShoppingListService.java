package com.fcgl.madrid.shopping.service;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.payload.request.GetAllUserShoppingListsRequest;
import com.fcgl.madrid.shopping.payload.request.NewShoppingListRequest;
import com.fcgl.madrid.shopping.payload.request.UserIdRequest;
import com.fcgl.madrid.shopping.payload.response.InternalStatus;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.repository.ShoppingListRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;
    private Log logger;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepositoryy) {
        this.shoppingListRepository = shoppingListRepositoryy;
        this.logger = LogFactory.getLog(ShoppingListService.class);
    }

    public Response<ShoppingList> addShoppingList(NewShoppingListRequest request) {
        ShoppingList shoppingList = new ShoppingList(request.getUserId(), request.getName(),request.getActive());
        this.shoppingListRepository.save(shoppingList);
        return new Response<>(InternalStatus.OK, shoppingList);
    }

    public Response<ShoppingList> getActiveUserShoppingList(UserIdRequest request) {
        ShoppingList shoppingList = this.shoppingListRepository.findByActiveTrueAndUserId(request.getUserId());
        return new Response<>(InternalStatus.OK, shoppingList);
    }

    public Response<List<ShoppingList>> getAllUserShoppingLists(GetAllUserShoppingListsRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize());
        List<ShoppingList> shoppingLists = this.shoppingListRepository.findAllByUserIdOrderByCreatedDate(request.getUserId(), pageConfig);
        return new Response<>(InternalStatus.OK, shoppingLists);
    }


}
