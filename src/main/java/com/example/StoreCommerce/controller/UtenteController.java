package com.example.StoreCommerce.controller;

import com.example.StoreCommerce.model.Utente;
import com.example.StoreCommerce.repository.OrderRepository;
import com.example.StoreCommerce.repository.UtenteRepository;
import com.example.StoreCommerce.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // gestiamo il salvaggio utente tramite form jsp

    @PostMapping("/register")
    public  String registrazioneUtente(@RequestParam ("username") String username, @RequestParam ("password") String password, Model model){

       if (utenteService.controllaLogin(username, password)){
           return "redirect:/listProducts";
       }else{
           //se non corrisponde col db
           return "index";
       }
    }

}
