package com.example.convertapp.userorder;

public class order

{


    String name;
    byte[]image;
    int id;
    int price;
    int qnt;
    public order(String name, byte[] image, int id,int price,int qnt) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.price = price;
this.qnt=qnt;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }
}
