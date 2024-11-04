// SignupActivity.java
package com.example.practiceiv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth; // Firebase Authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Set the layout for the activity

        auth = FirebaseAuth.getInstance(); // Initialize Firebase Auth

        EditText emailField = findViewById(R.id.email); // Email input field
        EditText passwordField = findViewById(R.id.password); // Password input field
        Button signupButton = findViewById(R.id.signup_button); // Signup button

        // Set click listener for the signup button
        signupButton.setOnClickListener(v -> {
            String email = emailField.getText().toString(); // Get email input
            String password = passwordField.getText().toString(); // Get password input
            signupUser(email, password); // Call signup method
        });
    }

    // Method to create a new user with email and password
    private void signupUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Show success message
                        Toast.makeText(SignupActivity.this, "Registration Successful",
                                Toast.LENGTH_SHORT).show();
                        finish(); // Close the activity
                    } else {
                        // Show failure message
                        Toast.makeText(SignupActivity.this, "Registration Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
