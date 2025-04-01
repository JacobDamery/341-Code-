package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        // ✅ Anchor FAB into BottomAppBar
        setSupportActionBar(findViewById(R.id.bottom_app_bar));

        // ✅ Setup Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account)
                .setupBottomNavigation(bottomNav);

        // ✅ FAB Click -> Launch CreatePost
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}
