package com.example.StoreCommerce.service;

import com.example.StoreCommerce.model.Cart;
import com.example.StoreCommerce.model.Product;
import com.example.StoreCommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public void addToCart(Product product, int quantity){
        Cart cart = new Cart(product, quantity);
        cartRepository.save(cart);

    }

    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    public void removeProductCart(Product product){
        List<Cart> cart = cartRepository.findAll();
        Cart pEsistente = null;
        for (Cart c : cart) {
            if (c.getProduct().getId() == product.getId()) {
                pEsistente = c;
                break;
            }
        }

        if (pEsistente!= null) {
            // Se il prodotto è presente nel carrello lo rimuoviamo
            cartRepository.delete(pEsistente);
        }
    }

}
