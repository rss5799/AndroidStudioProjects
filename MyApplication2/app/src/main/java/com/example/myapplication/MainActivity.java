package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DogBreed> dogBreeds; // Declare dogBreeds variable
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView); // Initialize your ListView

        // Initialize the list of dog breeds
        dogBreeds = new ArrayList<>();

        // Add dog breeds to the list
        dogBreeds.add(new DogBreed("Labrador Retriever", "Friendly and outgoing", "Labradors are known for their friendly and outgoing nature. They are great family pets and are highly trainable."));
        dogBreeds.add(new DogBreed("German Shepherd", "Intelligent and versatile", "German Shepherds are known for their intelligence and versatility. They are often used in police and military roles."));
        dogBreeds.add(new DogBreed("Golden Retriever", "Intelligent and friendly", "Golden Retrievers are known for their intelligence and friendly demeanor. They are great with kids."));
        dogBreeds.add(new DogBreed("Bulldog", "Docile and willful", "Bulldogs are known for their docile and willful nature. They are very affectionate and loyal companions."));
        dogBreeds.add(new DogBreed("Beagle", "Curious and friendly", "Beagles are known for their curious and friendly nature. They are great family pets and love to explore."));

        // Adapter to show titles in the list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getTitles(dogBreeds));
        listView.setAdapter(adapter);

        // Set the onItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Pass the selected item to DetailActivity
                DogBreed selectedBreed = dogBreeds.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("selectedBreed", selectedBreed); // Pass the selected breed
                startActivity(intent);

                // Show a toast notification
                Toast.makeText(MainActivity.this, selectedBreed.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> getTitles(ArrayList<DogBreed> breeds) {
        ArrayList<String> titles = new ArrayList<>();
        for (DogBreed breed : breeds) {
            titles.add(breed.getTitle());
        }
        return titles;
    }
}
