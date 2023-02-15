package com.example.StoreCommerce.controller;


import com.example.StoreCommerce.service.ProductService;
import com.example.StoreCommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String HomePage(){
        return "index";
    }

    @RequestMapping(value = "/listProducts", method = RequestMethod.GET)
    public String getProducts(ModelMap modelMap) {
        List<Product> body = productService.listProducts();
        modelMap.put("catalogo", body);
        return "listProducts";
    }

}
