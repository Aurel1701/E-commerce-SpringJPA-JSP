package com.example.StoreCommerce.repository;

import com.example.StoreCommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
