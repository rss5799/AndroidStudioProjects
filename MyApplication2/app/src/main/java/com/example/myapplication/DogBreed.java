package com.example.myapplication; // Ensure this matches your package name

import java.io.Serializable;

public class DogBreed implements Serializable {
    private String title;
    private String subtitle;
    private String description;

    // Constructor
    public DogBreed(String title, String subtitle, String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }
}
