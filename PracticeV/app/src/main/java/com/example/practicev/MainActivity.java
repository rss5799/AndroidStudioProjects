package com.example.practicev;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.practicev.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("House I grew up in");

        binding.fab.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "FAB Clicked!", Toast.LENGTH_SHORT).show()
        );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_container);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_map) {
                Toast.makeText(MainActivity.this, "Map clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_settings) {
                Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(38.78176386961602, -76.8892122745587);  // Coordinates of the house
        googleMap.addMarker(new MarkerOptions().position(location).title("House I grew up in!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f));
    }
}
