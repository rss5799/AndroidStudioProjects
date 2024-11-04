// PokemonAdapter.java
package com.example.practiceiv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private final List<String> pokemonList; // List of Pokémon names

    public PokemonAdapter(List<String> pokemonList) {
        this.pokemonList = pokemonList; // Constructor to initialize the list
    }

    // ViewHolder class to hold the views for each item
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // TextView for displaying Pokémon name

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view); // Initialize TextView
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and create a ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind the Pokémon name to the TextView
        holder.textView.setText(pokemonList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size(); // Return the total number of items
    }
}
