package com.example.StoreCommerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrello")

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Utente utente;

    @OneToMany(mappedBy = "cart")
    private List<CartEntry> cartProducts = new ArrayList<>();

    @Column(name = "is_paid")
    private boolean isPaid;



    public Cart() {
    }

    public Cart(int id, List<CartEntry> cartProducts) {
        this.id = id;
        this.cartProducts = cartProducts;
    }

    public Cart(Utente utente, List<CartEntry> cartProducts) {
        this.utente = utente;
        this.cartProducts = cartProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartEntry> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartEntry> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
