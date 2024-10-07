package com.example.dogbreeds;

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

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for subtitle
    public String getSubtitle() {
        return subtitle;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }
}
