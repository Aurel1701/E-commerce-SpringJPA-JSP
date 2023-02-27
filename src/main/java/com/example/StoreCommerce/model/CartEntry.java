package com.example.StoreCommerce.model;

import javax.persistence.*;
@Entity
@Table(name = "carrello_prodotti")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Product product;

    @Column(name = "quantita")
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "carrello_id")
    private Cart cart;

    public CartEntry() {
    }

    public CartEntry(Product product, int quantita, Cart cart) {
        this.product = product;
        this.quantita = quantita;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
