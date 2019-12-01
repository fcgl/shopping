package com.fcgl.madrid.shopping.service;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.dataModel.ShoppingProduct;
import com.fcgl.madrid.shopping.payload.request.*;
import com.fcgl.madrid.shopping.payload.response.InternalStatus;
import com.fcgl.madrid.shopping.payload.response.Response;
import com.fcgl.madrid.shopping.repository.ShoppingListRepository;
import com.fcgl.madrid.shopping.repository.ShoppingProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingProductService {

    private ShoppingProductRepository shoppingProductRepository;
    private ShoppingListRepository shoppingListRepository;
    private Log logger;

    @Autowired
    public ShoppingProductService(ShoppingProductRepository shoppingProductRepository, ShoppingListRepository shoppingListRepository) {
        this.shoppingProductRepository = shoppingProductRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.logger = LogFactory.getLog(ShoppingProductService.class);
    }

    public Response<ShoppingProduct> addShoppingProduct(NewShoppingProductRequest request) {
        Optional<ShoppingList> shoppingListOptional;
        if (request.getActive()) {
            shoppingListOptional = this.shoppingListRepository.findByActiveTrueAndUserId(request.getUserId());
        } else {
            shoppingListOptional = this.shoppingListRepository.findById(request.getShoppingListId());
        }
        if (!shoppingListOptional.isPresent()) {
            return new Response<>(InternalStatus.NOT_FOUND, null);
        }
        ShoppingList shoppingList = shoppingListOptional.get();
        updateShoppingListOnProductAddition(request, shoppingList);
        ShoppingProduct shoppingProduct =
                new ShoppingProduct(
                        request.getUserId(), shoppingList, request.getProductName(),
                        request.getRecommendedPrice(), request.getRecommendedStoreId(), request.getRecommendedProductId()
                );
        this.shoppingProductRepository.save(shoppingProduct);
        return new Response<>(InternalStatus.OK, shoppingProduct);
    }

    public Response<List<ShoppingProduct>> getUserShoppingProduct(GetUserShoppingProductRequest request) {
        List<ShoppingProduct> shoppingProducts = this.shoppingProductRepository.findAllByUserIdAndShoppingListId(
                request.getUserId(), request.getShoppingListId());
        return new Response<>(InternalStatus.OK, shoppingProducts);

    }

    public Response<ShoppingProduct> toggleCompleted(ToggleProductCompletedRequest request) {
        Optional<ShoppingProduct> shoppingProductOptional = this.shoppingProductRepository.findById(request.getProductId());
        if (!shoppingProductOptional.isPresent()) {
            return new Response<>(InternalStatus.NOT_FOUND, null);
        }
        ShoppingProduct shoppingProduct = shoppingProductOptional.get();
        shoppingProduct.setCompleted(!shoppingProduct.getCompleted());
        this.shoppingProductRepository.save(shoppingProduct);
        return new Response<>(InternalStatus.OK, shoppingProduct);
    }

    public Response<ShoppingProduct> updateName(UpdateNameRequest request) {
        ShoppingProduct shoppingProduct = this.shoppingProductRepository.findByIdAndUserId(request.getShoppingProductId(), request.getUserId());
        if (shoppingProduct == null) {
            return new Response<>(InternalStatus.NOT_FOUND, null);
        }
        ShoppingList shoppingList = shoppingProduct.getShoppingList();
        shoppingProduct.setRecommendedPrice(request.getRecommendedPrice());
        shoppingProduct.setRecommendedProductId(request.getRecommendedProductId());
        shoppingProduct.setRecommendedStoreId(request.getRecommendedStoreId());
        Boolean recommendationComplete = request.getRecommendedPrice() != null && request.getRecommendedProductId() != null && request.getRecommendedStoreId() != null;
        shoppingProduct.setRecommendationComplete(recommendationComplete);
        this.shoppingProductRepository.save(shoppingProduct);
        shoppingList.setSummary(getNewShoppingListSummary(shoppingList.getId(), shoppingList.getUserId()));
        this.shoppingListRepository.save(shoppingList);
        return  new Response<>(InternalStatus.OK, shoppingProduct);
    }

    private void updateShoppingListOnProductAddition(NewShoppingProductRequest request, ShoppingList shoppingList) {
        if (request.getRecommendedPrice() != null) {
            shoppingList.setTotalPrice(shoppingList.getTotalPrice() + request.getRecommendedPrice());
        }
        String shoppingListSummary = shoppingList.getSummary();
        if (shoppingListSummary.length() == 0) {
            shoppingList.setSummary(request.getProductName());
        } else {
            shoppingList.setSummary(shoppingList.getSummary() + ", " + request.getProductName());
        }

        this.shoppingListRepository.save(shoppingList);
    }

    private String getNewShoppingListSummary(Long shoppingListId, Long userId) {
        List<ShoppingProduct> shoppingProducts = this.shoppingProductRepository.findAllByUserIdAndShoppingListId(userId, shoppingListId);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < shoppingProducts.size(); i++) {
            String currentName = shoppingProducts.get(i).getProductName();
            builder.append(currentName);
            if (builder.length() > 0) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }



}
