package com.example.practiceiii;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> selectedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        recyclerView = findViewById(R.id.selected_products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Product[] selectedProductsArray = (Product[]) getIntent().getSerializableExtra("selected_products");

        if (selectedProductsArray != null) {
            selectedProducts = new ArrayList<>(Arrays.asList(selectedProductsArray));
            productAdapter = new ProductAdapter(this, selectedProducts);
            recyclerView.setAdapter(productAdapter);
        }

        Button emailButton = findViewById(R.id.email_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        StringBuilder emailBody = new StringBuilder();
        for (Product product : selectedProducts) {
            emailBody.append("Product Name: ").append(product.name)
                    .append(", Price: ").append(product.price)
                    .append("\n");
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sweng888mobileapps@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.toString());

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(emailIntent, "Send email using:"), 1);
        } else {
            Toast.makeText(this, "No email app found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(this, "Email sent successfully!", Toast.LENGTH_SHORT).show();

            selectedProducts.clear();
            productAdapter.notifyDataSetChanged();

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
