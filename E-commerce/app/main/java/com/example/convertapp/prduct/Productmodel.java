package com.example.convertapp.prduct;

public class Productmodel {

    String name;
    byte[]image;
    int id;
    int price;
    int quantaty;
    public Productmodel(String name, byte[] image, int id,int price,int quantaty) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.price = price;
        this.quantaty = quantaty;

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantaty() {
        return quantaty;
    }

    public void setQuantaty(int quantaty) {
        this.quantaty = quantaty;
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

}
