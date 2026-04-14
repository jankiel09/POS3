package com.example.pos3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText username, password;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        dbHelper = new DatabaseHelper(this);
        btnLogin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE,
                    new String[]{DatabaseHelper.COLUMN_EMP_ROLE},
                    DatabaseHelper.COLUMN_EMP_USER + "=? AND " + DatabaseHelper.COLUMN_EMP_PASS + "=?",
                    new String[]{user, pass}, null, null, null);

            if(cursor != null && cursor.moveToFirst()){

                String role = cursor.getString(0);
                cursor.close();

                dbHelper.AuditLog("User " + user + " logged in as " + role);

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                intent.putExtra("ROLE", role);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
                }else{
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);


    }
}
