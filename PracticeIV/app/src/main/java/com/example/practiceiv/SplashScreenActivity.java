// SplashScreenActivity.java
package com.example.practiceiv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); // Set the content view to the splash screen layout

        Handler handler = new Handler();
        // Post a delayed runnable to start LoginActivity after a 2-second delay
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent); // Start the LoginActivity
            finish(); // Finish the current activity to prevent returning to it
        }, 2000); // 2 second delay
    }
}
