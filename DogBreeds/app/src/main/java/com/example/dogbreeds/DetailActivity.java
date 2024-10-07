package com.example.dogbreeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextView);
        subtitleTextView = findViewById(R.id.subtitleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        backButton = findViewById(R.id.backButton);

        // Get the intent data
        DogBreed selectedBreed = (DogBreed) getIntent().getSerializableExtra("selectedBreed");

        // Set the title, subtitle, and description
        if (selectedBreed != null) {
            titleTextView.setText(selectedBreed.getTitle());
            subtitleTextView.setText(selectedBreed.getSubtitle());
            descriptionTextView.setText(selectedBreed.getDescription());
        }

        // Set an OnClickListener on the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getParent();
                if (mainActivity != null) {
                    mainActivity.setReturnedFromDetail(true);
                }
                finish();
            }
        });
    }
}
