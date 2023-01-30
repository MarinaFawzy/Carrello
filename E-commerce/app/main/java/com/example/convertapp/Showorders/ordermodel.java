package com.example.convertapp.Showorders;

public class ordermodel {



        String username;
    String date;
    String products;

    int id;

    public ordermodel(String username, String date, String products, int id) {
        this.username = username;
        this.date = date;
        this.products = products;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
