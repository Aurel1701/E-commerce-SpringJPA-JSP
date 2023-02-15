package com.example.StoreCommerce.controller;

import com.example.StoreCommerce.model.Cart;
import com.example.StoreCommerce.model.Product;
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

    @PostMapping("/addCarrello")
    public String addToCart(@RequestParam(value = "id") Integer id, HttpServletRequest httpServletRequest, ModelMap model) {
        Integer q = Integer.parseInt(httpServletRequest.getParameter("quantity"));  // converto l'input utente da string a int
        Product product = productService.getProductById(id);

        List<Cart> cart = cartService.findAllCart();

        Cart prodottoEsistente = null;

        for (Cart c : cart) {
        if (c.getProduct().getId() == product.getId()) {
              prodottoEsistente = c;
            break;
        }
        }

        if ( prodottoEsistente != null) {  //
            // Se il prodotto è già presente nel carrello  incrementiamo la quantità
            prodottoEsistente.setQuantity( prodottoEsistente.getQuantity() + q);
            cartRepository.save(prodottoEsistente); // salvo l'aggiunta
        } else {
            // Altrimenti aggiungiamo il prodotto al carrello con la quantità richiesta
            cartService.addToCart(product, q);
        }

        model.put("carrello",cartService.findAllCart());
        return "carrello";
    }


    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam(value = "id") Integer id, ModelMap model) {
        Product product = productService.getProductById(id);
        cartService.removeProductCart(product);
        model.put("carrello", cartService.findAllCart());
        return "carrello";
    }


    @GetMapping("/checkout")
    public String showCheckout(ModelMap modelMap) {
        List<Cart> body = cartService.findAllCart();
        double totale = 0;
        for (Cart prodotto : body) { // ciclo il carrello coi prodotti scelti nella lista body
            totale += prodotto.getProduct().getPrice() * prodotto.getQuantity();
        }
        modelMap.put("carrello", body);
        modelMap.put("totale", totale);
        return "checkout";
    }




}
