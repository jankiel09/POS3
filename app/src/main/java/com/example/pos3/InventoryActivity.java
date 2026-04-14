package com.example.pos3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pos3.SalesActivity.SalesActivity;
import com.example.pos3.datamodels.Inventory;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView rvInventory;
    InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        db = new DatabaseHelper(this);
        rvInventory = findViewById(R.id.rvInventory);


        loadInventory();


        setupNavigation();
    }

    private void loadInventory() {
        List<Inventory> list = db.getAllInventory();
        adapter = new InventoryAdapter(list);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));
        rvInventory.setAdapter(adapter);
    }

    private void setupNavigation() {
        findViewById(R.id.btnNavCheckout).setOnClickListener(v -> {
            startActivity(new Intent(this, SalesActivity.class));
            overridePendingTransition(0,0);
        });
        findViewById(R.id.btnNavOrders).setOnClickListener(v -> {
            startActivity(new Intent(this, OrdersActivity.class));
            overridePendingTransition(0,0);
        });
    }
}