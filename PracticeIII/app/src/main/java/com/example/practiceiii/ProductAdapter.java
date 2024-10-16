package com.example.practiceiii;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private List<Product> selectedProducts = new ArrayList<>();

    // Constructor for MainActivity
    public ProductAdapter(MainActivity mainActivity, List<Product> productList) {
        this.productList = productList;
    }

    // Overloaded constructor for SecondActivity
    public ProductAdapter(SecondActivity secondActivity, List<Product> selectedProducts) {
        this.productList = selectedProducts; // Use selected products list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);

        holder.itemView.setOnClickListener(v -> {
            if (selectedProducts.contains(product)) {
                selectedProducts.remove(product);
                holder.itemView.setBackgroundColor(Color.WHITE); // Deselect
            } else {
                selectedProducts.add(product);
                holder.itemView.setBackgroundColor(Color.LTGRAY); // Select
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
        }

        public void bind(Product product) {
            productName.setText(product.name);
            productPrice.setText(String.valueOf(product.price));
            productImage.setImageResource(product.imageResource); // Set the image resource
        }
    }

    // Method to retrieve selected products
    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }
}
