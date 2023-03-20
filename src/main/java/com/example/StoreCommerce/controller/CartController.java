package com.example.StoreCommerce.controller;

import com.example.StoreCommerce.model.Cart;
import com.example.StoreCommerce.model.CartEntry;
import com.example.StoreCommerce.model.Product;
import com.example.StoreCommerce.model.Utente;
import com.example.StoreCommerce.repository.CartEntryRepository;
import com.example.StoreCommerce.repository.CartRepository;
import com.example.StoreCommerce.repository.UtenteRepository;
import com.example.StoreCommerce.service.CartService;
import com.example.StoreCommerce.service.ProductService;
import com.example.StoreCommerce.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
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
    @Autowired
    private UtenteRepository utenteRepository;

    private int idCart;

    // recupero id carrello


    @PostMapping("/addCarrello")
    public String addToCart(@RequestParam(value = "id")  Integer id, HttpServletRequest httpServletRequest, ModelMap model) {
        Integer q = Integer.parseInt(httpServletRequest.getParameter("quantity"));  // converto l'input utente da string a int
        Cart c = cartService.addToCart(id,q);
        idCart = c.getId();
        List<CartEntry> cartEntries = cartEntryRepository.findAll();
        List<CartEntry> cartEntryList = new ArrayList<>();
        for (CartEntry cartEntry : cartEntries) {
            if (cartEntry.getCart().getId() == c.getId()) {
                cartEntryList.add(cartEntry);
            }
        }

        model.put("cartentry", cartEntryList); // x carrello
        return "carrello";
    }


   @PostMapping("/removeFromCart")
   public String removeFromCart(@RequestParam(value = "id") Integer id, ModelMap model) {
       cartService.removeFromCart(id,idCart);
       List<CartEntry> cartEntries = cartEntryRepository.findAll();
       List<CartEntry> cartEntryList = new ArrayList<>();
       for (CartEntry cartEntry : cartEntries) {
           if (cartEntry.getCart().getId() == idCart) {
               cartEntryList.add(cartEntry);
           }
       }
       model.put("cartentry", cartEntryList);
       return "carrello";
   }



    @GetMapping("/checkout")
    public String showCheckout(ModelMap modelMap) {
        List<CartEntry> cartEntries = cartEntryRepository.findAll();
        List<CartEntry> cartEntryList = new ArrayList<>();
        for (CartEntry cartEntry : cartEntries) {
            if (cartEntry.getCart().getId() == idCart) {
                cartEntryList.add(cartEntry);
            }
        }
        double totale = 0;
        for (CartEntry entry : cartEntryList) {
            totale += entry.getProduct().getPrezzo() * entry.getQuantita();
        }
        modelMap.put("cartentry", cartEntryList);
        modelMap.put("totale", totale);
        return "checkout";
    }

    @PostMapping("/chiudiOrdine")
    public String saveOrder(ModelMap model){
     /*   List<Utente> utenteList = utenteRepository.findAll();
        String username = utenteList.iterator().next().getUsername();*/
        String username = UtenteService.usernameLog;
       cartService.saveOrder(username,idCart);
       return "index";
    }




}
