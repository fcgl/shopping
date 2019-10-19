package com.fcgl.madrid.shopping.repository;

import com.fcgl.madrid.shopping.dataModel.ShoppingProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Long> {

    public List<ShoppingProduct> findAllByUserIdAndShoppingListId(Long userId, Long shoppingListId);

    public ShoppingProduct findByIdAndUserId(Long id, Long userId);

}
