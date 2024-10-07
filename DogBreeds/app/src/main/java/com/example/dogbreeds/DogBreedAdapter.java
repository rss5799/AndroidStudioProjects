package com.example.dogbreeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DogBreedAdapter extends ArrayAdapter<DogBreed> {
    private final Context context;
    private final ArrayList<DogBreed> breeds;

    public DogBreedAdapter(Context context, ArrayList<DogBreed> breeds) {
        super(context, R.layout.list_item_dog_breed, breeds);
        this.context = context;
        this.breeds = breeds;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for the list item
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_dog_breed, parent, false);
        }

        // Get the current breed
        DogBreed currentBreed = breeds.get(position);

        // Set the title and subtitle
        TextView titleTextView = convertView.findViewById(R.id.dog_breed_title);
        TextView subtitleTextView = convertView.findViewById(R.id.dog_breed_subtitle);

        titleTextView.setText(currentBreed.getTitle());
        subtitleTextView.setText(currentBreed.getSubtitle());

        return convertView;
    }
}
