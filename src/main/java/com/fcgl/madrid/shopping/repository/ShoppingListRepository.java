package com.fcgl.madrid.shopping.repository;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import com.fcgl.madrid.shopping.payload.response.ShoppingListNoProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    public List<ShoppingListNoProduct> findAllByUserIdOrderByCreatedDate(Long userId, Pageable pageable);
    public Optional<ShoppingList> findByActiveTrueAndUserId(Long userId);
    public Optional<ShoppingList> findByIdAndUserId(Long id, Long userId);
}
