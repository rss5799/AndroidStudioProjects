package com.example.practiceiii;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize database helper and get product list
        dbHelper = new DatabaseHelper(this);
        productList = dbHelper.getAllProducts(); // Fetch all products from the database

        // Set up adapter and bind it to RecyclerView
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Setup the Select button
        Button selectButton = findViewById(R.id.select_button);
        selectButton.setOnClickListener(v -> {
            List<Product> selectedProducts = productAdapter.getSelectedProducts();
            if (selectedProducts.size() < 3) {
                Toast.makeText(MainActivity.this, "Please select at least 3 products.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("selected_products", selectedProducts.toArray(new Product[0])); // Pass selected products
                startActivity(intent);
            }
        });

        // Call to insert sample products if needed
        insertSampleProducts();
    }

    private void insertSampleProducts() {
        // Create sample products
        Product product1 = new Product(0, "Product 1", "Description 1", "Seller 1", 10.99f, android.R.drawable.ic_menu_camera);
        Product product2 = new Product(0, "Product 2", "Description 2", "Seller 2", 20.99f, android.R.drawable.ic_menu_gallery);
        Product product3 = new Product(0, "Product 3", "Description 3", "Seller 3", 30.99f, android.R.drawable.ic_menu_send);

        // Insert products into the database
        dbHelper.insertProduct(product1);
        dbHelper.insertProduct(product2);
        dbHelper.insertProduct(product3);
    }
}
