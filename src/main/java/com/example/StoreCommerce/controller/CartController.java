package com.example.StoreCommerce.controller;

import com.example.StoreCommerce.model.Cart;
import com.example.StoreCommerce.model.CartEntry;
import com.example.StoreCommerce.model.Product;
import com.example.StoreCommerce.repository.CartEntryRepository;
import com.example.StoreCommerce.repository.CartRepository;
import com.example.StoreCommerce.service.CartService;
import com.example.StoreCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @PostMapping("/addCarrello")
    public String addToCart(@RequestParam(value = "id")  Integer id, HttpServletRequest httpServletRequest, ModelMap model) throws Exception {

        Integer q = Integer.parseInt(httpServletRequest.getParameter("quantity"));  // converto l'input utente da string a int
        cartService.addToCart(id,q);
        model.put("cartentry",cartEntryRepository.findAll());
        return "carrello";
    }


   @PostMapping("/removeFromCart")
   public String removeFromCart(@RequestParam(value = "id") Integer id, ModelMap model) throws Exception {
       cartService.removeFromCart(id);
       model.put("cartentry", cartEntryRepository.findAll());
       return "carrello";
   }



    @GetMapping("/checkout")
    public String showCheckout(ModelMap modelMap) {
        List<CartEntry> cartEntries = cartEntryRepository.findAll();
        double totale = 0;
        for (CartEntry entry : cartEntries) {
            totale += entry.getProduct().getPrezzo() * entry.getQuantita();
        }
        modelMap.put("cartentry", cartEntries);
        modelMap.put("totale", totale);
        return "checkout";
    }

    /*@PostMapping("/chiudiOrdine")
    public void chiudiOrdine() throws Exception {
        cartService.chiudiOrdine();
    }*/




}
