package com.example.practiceiii;

import android.media.Image;
import android.os.Bundle;

public class Product {
    public int id;
    public String name;
    public String description;
    public String seller;
    public float price;
    public int imageResource;

    public Product(int id, String name, String description, String seller, float price, int imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.imageResource = imageResource;
    }
}
