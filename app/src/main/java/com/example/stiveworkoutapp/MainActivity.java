package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            WindowInsetsCompat systemBars = insets;
            int left = systemBars.getInsets(WindowInsetsCompat.Type.systemBars()).left;
            int top = systemBars.getInsets(WindowInsetsCompat.Type.systemBars()).top;
            int right = systemBars.getInsets(WindowInsetsCompat.Type.systemBars()).right;
            int bottom = systemBars.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setPadding(left, top, right, bottom);
            return insets;
        });

        // ✅ Bottom Nav Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationHandler bottomNavigationHandler = new BottomNavigationHandler(this, R.id.nav_home);
        bottomNavigationHandler.setupBottomNavigation(bottomNavigationView);

        // ✅ FAB Click to Launch CreatePost
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}
