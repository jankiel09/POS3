package com.example.pos3;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pos3.SalesActivity.SalesActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnSales, btnOrders, btnInventory, btnEmployees, btnAudit, btnReports, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        onCLickListeners();

        String role = getIntent().getStringExtra("ROLE");
        if (role == null) role = "";
        if(role.equals("admin")){
            btnSales.setVisibility(Button.VISIBLE);
            btnOrders.setVisibility(Button.VISIBLE);
            btnInventory.setVisibility(Button.VISIBLE);
            btnEmployees.setVisibility(Button.VISIBLE);
            btnAudit.setVisibility(Button.VISIBLE);
            btnReports.setVisibility(Button.VISIBLE);
            btnLogout.setVisibility(Button.VISIBLE);
        } else if (role.equals("inventory")){
            btnSales.setVisibility(Button.GONE);
            btnOrders.setVisibility(Button.GONE);
            btnInventory.setVisibility(Button.VISIBLE);
            btnEmployees.setVisibility(Button.GONE);
            btnAudit.setVisibility(Button.GONE);
            btnReports.setVisibility(Button.GONE);
            btnLogout.setVisibility(Button.VISIBLE);
        } else if (role.equals("sales")){
            btnSales.setVisibility(Button.VISIBLE);
            btnOrders.setVisibility(Button.VISIBLE);
            btnInventory.setVisibility(Button.GONE);
            btnEmployees.setVisibility(Button.GONE);
            btnAudit.setVisibility(Button.GONE);
            btnReports.setVisibility(Button.GONE);
            btnLogout.setVisibility(Button.VISIBLE);
        } else if (role.equals("assistant")){
            btnSales.setVisibility(Button.VISIBLE);
            btnOrders.setVisibility(Button.VISIBLE);
            btnInventory.setVisibility(Button.VISIBLE);
            btnEmployees.setVisibility(Button.GONE);
            btnAudit.setVisibility(Button.VISIBLE);
            btnReports.setVisibility(Button.VISIBLE);
            btnLogout.setVisibility(Button.VISIBLE);
        }



    }

    private void initView(){
        btnSales = findViewById(R.id.btnSales);
        btnOrders = findViewById(R.id.btnOrders);
        btnInventory = findViewById(R.id.btnInventory);
        btnEmployees = findViewById(R.id.btnEmployees);
        btnAudit = findViewById(R.id.btnAudit);
        btnReports = findViewById(R.id.btnReports);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void onCLickListeners(){
        btnLogout.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, LoginActivity.class));
        });
        btnSales.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, SalesActivity.class));
        });
        btnOrders.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, OrdersActivity.class));
        });
        btnInventory.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, InventoryActivity.class));
        });
        btnEmployees.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, EmployeesActivity.class));
        });
        btnAudit.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, AuditActivity.class));
        });
        btnReports.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MenuActivity.this, ReportsActivity.class));
        });


    }
}