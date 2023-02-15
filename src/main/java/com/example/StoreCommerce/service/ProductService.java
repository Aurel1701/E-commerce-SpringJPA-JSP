package com.example.StoreCommerce.service;


import com.example.StoreCommerce.repository.ProductRepository;
import com.example.StoreCommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    public List<Product> listProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

}
