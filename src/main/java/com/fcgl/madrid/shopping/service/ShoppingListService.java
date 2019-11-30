package com.fcgl.madrid.shopping.service;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.dataModel.ShoppingProduct;
import com.fcgl.madrid.shopping.payload.request.*;
import com.fcgl.madrid.shopping.payload.response.InternalStatus;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.payload.response.ShoppingListNoProduct;
import com.fcgl.madrid.shopping.payload.response.ShoppingListProductResponse;
import com.fcgl.madrid.shopping.repository.ShoppingListRepository;
import com.fcgl.madrid.shopping.repository.ShoppingProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;
    private ShoppingProductRepository shoppingProductRepository;
    private Log logger;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingProductRepository shoppingProductRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingProductRepository = shoppingProductRepository;
        this.logger = LogFactory.getLog(ShoppingListService.class);
    }

    //TODO: Make all others inactive
    public Response<ShoppingList> addShoppingList(NewShoppingListRequest request) {
        ShoppingList shoppingList = new ShoppingList(request.getUserId(), request.getName(),request.getActive());
        this.shoppingListRepository.save(shoppingList);
        return new Response<>(InternalStatus.OK, shoppingList);
    }

    public ResponseEntity<Response<ShoppingList>> getActiveUserShoppingList(UserIdRequest request) {
        Optional<ShoppingList> shoppingListOptional = this.shoppingListRepository.findByActiveTrueAndUserId(request.getUserId());
        ShoppingList shoppingList = null;
        if (shoppingListOptional.isPresent()) {
            shoppingList = shoppingListOptional.get();
        } else {
            shoppingList = new ShoppingList(request.getUserId(), "default",true);
            this.shoppingListRepository.save(shoppingList);
        }
        Response<ShoppingList> response = new Response<>(InternalStatus.OK, shoppingList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Response<List<ShoppingListNoProduct>> getAllUserShoppingLists(GetAllUserShoppingListsRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize());
        List<ShoppingListNoProduct> shoppingLists = this.shoppingListRepository.findAllByUserIdOrderByCreatedDate(request.getUserId(), pageConfig);
        return new Response<>(InternalStatus.OK, shoppingLists);
    }

    public ResponseEntity<Response<ShoppingList>> getUserShoppingList(GetUserShoppingListRequest request) {
        Optional<ShoppingList> shoppingListOptional = this.shoppingListRepository.findByIdAndUserId(request.getShoppingListId(), request.getUserId());
        if (!shoppingListOptional.isPresent()) {
            Response<ShoppingList> response = new Response<>(InternalStatus.NOT_FOUND, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        ShoppingList shoppingList = shoppingListOptional.get();
        Response<ShoppingList> response = new Response<ShoppingList>(InternalStatus.OK, shoppingList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response<ShoppingListProductResponse>> addShoppingListAndProducts(NewShoppingListWithProductsRequest request) {
        ShoppingList shoppingList = new ShoppingList(request.getUserId(), request.getName(),request.getActive());
        this.shoppingListRepository.save(shoppingList);
        StringBuilder builder = new StringBuilder("");
        List<ShoppingProduct> shoppingProducts = new ArrayList<>(request.getProducts().size());
        for (int i = 0; i < request.getProducts().size(); i++) {
            String productName = request.getProducts().get(i);
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(productName);
            ShoppingProduct shoppingProduct =
                    new ShoppingProduct(
                            request.getUserId(), shoppingList, productName
                    );
            this.shoppingProductRepository.save(shoppingProduct);
            shoppingProducts.add(shoppingProduct);
        }
        shoppingList.setSummary(builder.toString());
        this.shoppingListRepository.save(shoppingList);
        ShoppingListProductResponse responseBody = new ShoppingListProductResponse(shoppingList, shoppingProducts);
        Response<ShoppingListProductResponse> response = new Response<>(InternalStatus.OK, responseBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
