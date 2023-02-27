package com.example.StoreCommerce.repository;

import com.example.StoreCommerce.model.Cart;
import com.example.StoreCommerce.model.CartEntry;
import com.example.StoreCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartEntryRepository extends JpaRepository<CartEntry,Integer> {

    public Optional<CartEntry> findByProductAndCart(Product product, Cart cart);
}
