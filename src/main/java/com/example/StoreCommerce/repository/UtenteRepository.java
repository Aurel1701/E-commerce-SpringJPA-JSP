package com.example.StoreCommerce.repository;

import com.example.StoreCommerce.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,String> {
    public Utente findByUsername(String username);

}
