package com.example.StoreCommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Utente() {
    }

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
