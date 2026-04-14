package com.example.pos3.SalesActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos3.DatabaseHelper;
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
        setContentView(R.layout.salesui);
        db = new DatabaseHelper(this);

        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvVAT = findViewById(R.id.tvVAT);
        tvTotal = findViewById(R.id.tvTotal);
        cbDiscount = findViewById(R.id.cbDiscount);
        Button btnComplete = findViewById(R.id.btnComplete);
        cbDiscount.setOnCheckedChangeListener((v, isChecked) -> calculatePricing());

        btnComplete.setOnClickListener(v -> {
            if (cartList.isEmpty()) return;

            for (Cart item : cartList) {
                db.updateStock(item.getId(), item.getQuantity());
            }

            Toast.makeText(this, "Order Complete", Toast.LENGTH_SHORT).show();
            cartList.clear();
            calculatePricing();
        });
    }

    private void calculatePricing(){

        double subtotal = 0;
        for (Cart c : cartList) subtotal += c.getPrice() * c.getQuantity();


        double discount = cbDiscount.isChecked() ? subtotal * 0.2 : 0;
        double vat = subtotal * 0.12;
        double total = subtotal - discount + vat;

        tvSubtotal.setText(String.format("Subtotal: P%.2f", subtotal));
        tvVAT.setText(String.format("VAT: P%.2f", vat));
        tvTotal.setText(String.format("Total: P%.2f", total));
    }







}
