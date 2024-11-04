// PokemonFragment.java
package com.example.practiceiv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PokemonFragment extends Fragment {

    private RecyclerView recyclerView; // RecyclerView for displaying Pokémon
    private PokemonAdapter pokemonAdapter; // Adapter for the RecyclerView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout and initialize views
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        recyclerView = view.findViewById(R.id.recycler_view); // Initialize RecyclerView

        // Sample Pokémon list
        List<String> pokemonList = Arrays.asList("Pikachu", "Charmander", "Bulbasaur", "Squirtle");
        pokemonAdapter = new PokemonAdapter(pokemonList); // Create adapter with Pokémon list

        // Set layout manager and adapter for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(pokemonAdapter);

        return view; // Return the inflated view
    }
}
