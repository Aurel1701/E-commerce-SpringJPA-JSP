package com.example.StoreCommerce.repository;

import com.example.StoreCommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
