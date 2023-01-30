package com.example.convertapp.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class cartmodel  {
    String name;
    byte[]image;
    int id;
    int price;
int qnt;
    public cartmodel(String name, byte[] image, int id,int price) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.price = price;


    }




    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
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

  }
