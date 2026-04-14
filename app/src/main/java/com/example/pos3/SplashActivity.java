package com.example.pos3;

import android.content.Intent;import android.os.Bundle;import android.os.Handler;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.ImageView;import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    Animation anim;
    ImageView mcdologo;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
            anim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
            mcdologo.setAnimation(anim);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Start the LoginActivity after the splash screen duration
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish(); // Finish the SplashActivity so it's removed from the back stack
                }
            }, 2500); // 2500 milliseconds = 2.5 seconds
    }

    private void initView(){
        mcdologo = findViewById(R.id.mcdologo);
        tvWelcome = findViewById(R.id.tvWelcome);
    }
}