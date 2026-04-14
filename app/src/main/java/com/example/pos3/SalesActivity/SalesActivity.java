package com.example.pos3.SalesActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pos3.DatabaseHelper;
import com.example.pos3.OrdersActivity;
import com.example.pos3.R;
import com.example.pos3.datamodels.Cart;

import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    List<Cart> cartList = new ArrayList<>();
    DatabaseHelper db;
    TextView tvSubtotal, tvVAT, tvTotal;
    CheckBox cbDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        db = new DatabaseHelper(this);

        // Initialize UI
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvVAT = findViewById(R.id.tvVAT);
        tvTotal = findViewById(R.id.tvTotal);
        cbDiscount = findViewById(R.id.cbDiscount);
        Button btnComplete = findViewById(R.id.btnComplete);

        // Listeners
        cbDiscount.setOnCheckedChangeListener((v, isChecked) -> calculatePricing());

        btnComplete.setOnClickListener(v -> {
            if (cartList.isEmpty()) {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Loop through cart and subtract stock in SQL (Order Module)
            for (Cart item : cartList) {
                db.updateStock(item.getId(), item.getQuantity());
            }

            Toast.makeText(this, "Order Complete & Stock Updated", Toast.LENGTH_SHORT).show();
            cartList.clear();
            calculatePricing();
        });

        // Initialize Bottom Navigation
        setupBottomNavigation();
    }

    private void calculatePricing() {
        double subtotal = 0;
        for (Cart c : cartList) {
            subtotal += c.getPrice() * c.getQuantity();
        }

        // Pricing Logic: 20% Discount if Senior/PWD
        double discount = cbDiscount.isChecked() ? subtotal * 0.20 : 0;
        double taxableAmount = subtotal - discount;

        // 12% VAT on the taxable amount (Standard POS logic)
        double vat = taxableAmount * 0.12;
        double total = taxableAmount + vat;

        tvSubtotal.setText(String.format("Subtotal: P%.2f", subtotal));
        tvVAT.setText(String.format("VAT (12%%): P%.2f", vat));
        tvTotal.setText(String.format("Total: P%.2f", total));
    }

    private void setupBottomNavigation() {
        // Find navigation items
        View btnNavCheckout = findViewById(R.id.btnNavCheckout);
        View btnNavOrders = findViewById(R.id.btnNavOrders);


        // Navigate to Orders Screen
        if (btnNavOrders != null) {
            btnNavOrders.setOnClickListener(v -> {
                Intent intent = new Intent(this, OrdersActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0); // No animation for "fast" feel
            });
        }

        // Highlight Checkout as active
        if (btnNavCheckout != null) {
            btnNavCheckout.setOnClickListener(v -> {
                // Already on Sales/Checkout screen
            });
        }
    }
}