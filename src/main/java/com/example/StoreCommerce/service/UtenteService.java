package com.example.StoreCommerce.service;

import com.example.StoreCommerce.model.Utente;
import com.example.StoreCommerce.repository.OrderRepository;
import com.example.StoreCommerce.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public static String usernameLog;

    public UtenteService() {
    }


    public Utente salvaggioUtente(Utente utente){
        return utenteRepository.save(utente);
    }

    public boolean controllaLogin(String username, String password){
        List<Utente> utentiRegistrati = utenteRepository.findAll();
        for (Utente utenteRegistrato : utentiRegistrati) {
            if(utenteRegistrato.getUsername().equals(username) && utenteRegistrato.getPassword().equals(password)){
                usernameLog = utenteRegistrato.getUsername();
                return true;
            }

        }

        return false;


    }
}
