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

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        List<String> pokemonList = Arrays.asList("Pikachu", "Charmander", "Bulbasaur", "Squirtle");
        pokemonAdapter = new PokemonAdapter(pokemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(pokemonAdapter);

        return view;
    }
}
