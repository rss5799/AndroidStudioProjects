package com.example.practiceiv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        Button openDrawerButton = findViewById(R.id.open_drawer_button);

        // Set up the ActionBar toggle for the drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Enable the home button in the ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set up the navigation controller with the NavigationView
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(navView, navHostFragment.getNavController());
        }

        // Handle navigation item selection
        navView.setNavigationItemSelectedListener(menuItem -> {
            Fragment selectedFragment = null;
            if (menuItem.getItemId() == R.id.nav_pokemon) {
                selectedFragment = new PokemonFragment();
            } else if (menuItem.getItemId() == R.id.nav_account) {
                selectedFragment = new AccountFragment();
            } else if (menuItem.getItemId() == R.id.nav_logout) {
                // Start LoginActivity when logout is selected
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            // Replace the current fragment with the selected fragment
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
            }
            drawerLayout.closeDrawers(); // Close the drawer after selection
            return true;
        });

        // Set up the button to open the navigation drawer
        openDrawerButton.setOnClickListener(v -> drawerLayout.openDrawer(navView));
    }
}
