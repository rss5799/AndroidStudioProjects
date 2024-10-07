package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextView);
        subtitleTextView = findViewById(R.id.subtitleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        // Get the intent data
        DogBreed selectedBreed = (DogBreed) getIntent().getSerializableExtra("selectedBreed");

        // Set the title, subtitle, and description
        if (selectedBreed != null) {
            titleTextView.setText(selectedBreed.getTitle());
            subtitleTextView.setText(selectedBreed.getSubtitle());
            descriptionTextView.setText(selectedBreed.getDescription());
        }
    }
}
