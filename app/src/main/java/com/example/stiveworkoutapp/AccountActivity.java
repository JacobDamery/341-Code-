package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account)
                .setupBottomNavigation(bottomNav);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Settings button click handler
        findViewById(R.id.settings_icon).setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        // No transition animation
            overridePendingTransition(0, 0);
        });

        // Friends button click handler
        findViewById(R.id.friends_icon).setOnClickListener(view -> {
            Intent intent = new Intent(this, FriendsAdapter.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}
