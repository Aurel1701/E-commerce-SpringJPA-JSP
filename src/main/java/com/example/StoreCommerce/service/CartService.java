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

    public Cart addToCart(int prodottoId, int quantita) {

        Optional<Product> product = productRepository.findById(prodottoId);

        Product product1 = product.get();

        /*List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();*/

        String username = UtenteService.usernameLog;


        //Optional<Cart> optionalCart = cartRepository.findByUtenteUsername(username);
        List<Cart> cartList = cartRepository.findAll();
        Cart c = new Cart();
        boolean isPresent = false;
        if (!cartList.isEmpty()) {
            for (Cart cart : cartList) {
                if (cart.getUtente().getUsername().equals(username) && !cart.isPaid()) {
                    c = cart;
                    isPresent = true;
                }
            }
        } else {
            c = new Cart(new Utente(username,""),new ArrayList<>());
            cartRepository.save(c);
        }

        if (!isPresent) {
            c = new Cart(new Utente(username,""),new ArrayList<>());
            cartRepository.save(c);
        }


        /*if(optionalCart.isEmpty()) {
            c = new Cart(new Utente(username,""),new ArrayList<>());
            cartRepository.save(c);
        } else {
            c = optionalCart.get();
        }*/

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

        return c;
    }

    public void removeFromCart(int prodottoId, int idCarrello) {

        Optional<Product> optionalProduct = productRepository.findById(prodottoId);
        Product product = optionalProduct.get();

        List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();

        Optional<Cart> optionalCart = cartRepository.findById(idCarrello);
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


    public void saveOrder(String username,int idCarrello) {

        Optional<Cart> carrelloUtente = cartRepository.findById(idCarrello);
       Optional<Utente> optionalUtente = utenteRepository.findById(username);

        if (carrelloUtente.isPresent() && optionalUtente.isPresent()) {
            Cart cart = carrelloUtente.get();
           Utente utente = optionalUtente.get();

            // creo nuovo ordine coi prodotti scelti dall'admin
            Order ordineUtente = new Order();
            ordineUtente.setCart(cart);
            ordineUtente.setUtente(utente);

            orderRepository.save(ordineUtente);

            // svuoto carrello
            //cart.getCartProducts().clear();
            cart.setPaid(true);

            cartRepository.save(cart);
        }

    }








}
