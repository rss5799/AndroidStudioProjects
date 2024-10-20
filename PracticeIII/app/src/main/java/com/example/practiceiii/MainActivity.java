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

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Insert sample products
        insertSampleProducts();

        // Get product list after inserting sample products
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
    }

    private void insertSampleProducts() {
        // Clear existing products if needed
        dbHelper.clearProducts();

        // Create sample products
        Product product1 = new Product(0, "Camera", "High-resolution camera for capturing stunning photos.", "Seller A", 299.99f, android.R.drawable.ic_menu_camera);
        Product product2 = new Product(0, "Gallery", "Photo gallery app for organizing your images.", "Seller B", 9.99f, android.R.drawable.ic_menu_gallery);
        Product product3 = new Product(0, "Paper Airplane", "A fun and interactive paper airplane launcher toy.", "Seller C", 15.49f, android.R.drawable.ic_menu_send);
        Product product4 = new Product(0, "Contact", "Easy-to-use contact management app.", "Seller D", 4.99f, android.R.drawable.ic_menu_info_details);
        Product product5 = new Product(0, "Preferences", "Settings app for customizing your device.", "Seller E", 0.99f, android.R.drawable.ic_menu_preferences);
        Product product6 = new Product(0, "Maps", "Navigation app with real-time traffic updates.", "Seller F", 14.99f, android.R.drawable.ic_menu_mylocation);
        Product product7 = new Product(0, "Settings", "Settings app for adjusting your device preferences.", "Seller I", 0.00f, android.R.drawable.ic_menu_set_as);

        // Insert products into the database
        dbHelper.insertProduct(product1);
        dbHelper.insertProduct(product2);
        dbHelper.insertProduct(product3);
        dbHelper.insertProduct(product4);
        dbHelper.insertProduct(product5);
        dbHelper.insertProduct(product6);
        dbHelper.insertProduct(product7);
    }

}
