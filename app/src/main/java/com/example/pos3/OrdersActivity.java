package com.example.pos3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager; // Correct import
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos3.SalesActivity.SalesActivity;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView rvOrders = findViewById(R.id.rvOrders);

        // FIX 1: Use the standard RecyclerView GridLayoutManager
        rvOrders.setLayoutManager(new GridLayoutManager(this, 4));

        // FIX 2: You must define these methods below
        setupBottomNavigation();
        loadOrdersFromDb();
    }

    private void setupBottomNavigation() {
        LinearLayout btnNavCheckout = findViewById(R.id.btnNavCheckout);
        btnNavCheckout.setOnClickListener(v -> {
            startActivity(new Intent(this, SalesActivity.class));
            overridePendingTransition(0, 0); // Smooth switch
        });
    }

    private void loadOrdersFromDb() {

    }
}