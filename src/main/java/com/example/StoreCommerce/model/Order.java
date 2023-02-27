package com.example.StoreCommerce.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "or_der")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carrello_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;




    public Order() {
    }

    public Order(Cart cart, Utente utente) {
        this.cart = cart;
        this.utente = utente;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
