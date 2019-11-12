package com.fcgl.madrid.shopping.repository;

import com.fcgl.madrid.shopping.dataModel.ShoppingList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    public List<ShoppingList> findAllByUserIdOrderByCreatedDate(Long userId, Pageable pageable);
    public ShoppingList findByActiveTrueAndUserId(Long userId);
}
