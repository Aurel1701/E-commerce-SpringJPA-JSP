package com.example.StoreCommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String sku;
    private double price;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;


    public Product() {
    }

    public Product(String name, String sku, double price, List<Cart> carts) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.carts = carts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", carts=" + carts +
                '}';
    }
}
