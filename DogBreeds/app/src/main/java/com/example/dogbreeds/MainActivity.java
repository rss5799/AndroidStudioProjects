package com.example.dogbreeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DogBreed> dogBreeds;
    private boolean returnedFromDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView); // Initialize ListView

        // Initialize the list of dog breeds
        dogBreeds = new ArrayList<>();

        // Add dog breeds to the list
        dogBreeds.add(new DogBreed("Labrador Retriever", "Friendly and outgoing", "Labradors are known for their friendly and outgoing nature. They are great family pets and are highly trainable."));
        dogBreeds.add(new DogBreed("German Shepherd", "Intelligent and versatile", "German Shepherds are known for their intelligence and versatility. They are often used in police and military roles."));
        dogBreeds.add(new DogBreed("Golden Retriever", "Intelligent and friendly", "Golden Retrievers are known for their intelligence and friendly demeanor. They are great with kids."));
        dogBreeds.add(new DogBreed("Bulldog", "Docile and willful", "Bulldogs are known for their docile and willful nature. They are very affectionate and loyal companions."));
        dogBreeds.add(new DogBreed("Beagle", "Curious and friendly", "Beagles are known for their curious and friendly nature. They are great family pets and love to explore."));

        // Adapter to show titles and subtitles in the list
        DogBreedAdapter adapter = new DogBreedAdapter(this, dogBreeds);
        listView.setAdapter(adapter);

        // Set the onItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DogBreed selectedBreed = dogBreeds.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("selectedBreed", selectedBreed);
                startActivity(intent);
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Show a Toast message...not working and I don't know why.
                Toast.makeText(MainActivity.this, "Operation Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (returnedFromDetail) {
            Toast.makeText(this, "Returned from Detail Activity", Toast.LENGTH_SHORT).show();
            returnedFromDetail = false;
        }
    }

    public void setReturnedFromDetail(boolean returned) {
        this.returnedFromDetail = returned;
    }
}
