package com.example.practiceiii;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast; // Import Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList; // Import ArrayList
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> selectedProducts; // Change to List<Product>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.selected_products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get selected products from the intent
        Product[] selectedProductsArray = (Product[]) getIntent().getSerializableExtra("selected_products");

        if (selectedProductsArray != null) {
            selectedProducts = new ArrayList<>(Arrays.asList(selectedProductsArray)); // Initialize the list
            // Set up adapter with selected products and bind it to RecyclerView
            productAdapter = new ProductAdapter(this, selectedProducts);
            recyclerView.setAdapter(productAdapter);
        }

        // Set up the email button
        Button emailButton = findViewById(R.id.email_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    private void sendEmail() {
        // Build the email body
        StringBuilder emailBody = new StringBuilder();
        for (Product product : selectedProducts) {
            emailBody.append("Product Name: ").append(product.name)
                    .append(", Price: ").append(product.price)
                    .append("\n");
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // Set the type to email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"barre1ba@gmail.com"}); // Replace with the actual recipient's email
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.toString());

        // Check if there's an app to handle the email intent
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email using:")); // Use chooser for better user experience

            // Show a Toast message indicating email was sent
            Toast.makeText(this, "Email sent successfully!", Toast.LENGTH_SHORT).show();

            // Clear the selected products list and notify the adapter
            selectedProducts.clear(); // Clear the list
            productAdapter.notifyDataSetChanged(); // Notify adapter of data change
        } else {
            // Show a Toast message if no email app is found
            Toast.makeText(this, "No email app found!", Toast.LENGTH_SHORT).show();
        }
    }
}
