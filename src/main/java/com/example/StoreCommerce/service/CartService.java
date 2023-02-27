package com.example.StoreCommerce.service;

import com.example.StoreCommerce.model.*;
import com.example.StoreCommerce.repository.*;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void addToCart(int prodottoId, int quantita) {

        Optional<Product> product = productRepository.findById(prodottoId);

        Product product1 = product.get();

        List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();

        Optional<Cart> optionalCart = cartRepository.findByUtenteUsername(username);
        Cart c;
        if(optionalCart.isEmpty()) {
            c = new Cart(new Utente(username,""),new ArrayList<>());
            cartRepository.save(c);
        } else {
            c = optionalCart.get();
        }

        Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductAndCart(product1, c);
        CartEntry cartEntry;

        if (optionalCartEntry.isPresent()) {
            cartEntry = optionalCartEntry.get();
            cartEntry.setQuantita(cartEntry.getQuantita() + quantita);
        } else {
            cartEntry = new CartEntry(product1,quantita,c);
            c.getCartProducts().add(cartEntry);
        }

        cartEntryRepository.save(cartEntry);

    }

    public void removeFromCart(int prodottoId) {

        Optional<Product> optionalProduct = productRepository.findById(prodottoId);
        Product product = optionalProduct.get();

        List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();

        Optional<Cart> optionalCart = cartRepository.findByUtenteUsername(username);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductAndCart(product, cart);
            if (optionalCartEntry.isPresent()) {
                CartEntry cartEntry = optionalCartEntry.get();
                cart.getCartProducts().remove(cartEntry);
                cartRepository.save(cart);
                cartEntryRepository.delete(cartEntry);
            }
        }
    }

    /*public void chiudiOrdine() {
        List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();

        Optional<Cart> optionalCart = cartRepository.findByUtenteUsername(username);
        Cart cart = optionalCart.get();

        Utente user = utenteRepository.findByUsername(username);

        Order order = new Order(cart,user);
        orderRepository.save(order);

        cart.getCartProducts().clear();
        cartRepository.save(cart);
    }*/


}
