package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountFeed extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_feed);

        // ✅ Link the FAB to the BottomAppBar so it nests in the cradle
        setSupportActionBar(findViewById(R.id.bottom_app_bar));

        // ✅ Setup Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account_feed)
                .setupBottomNavigation(bottomNav);

        // ✅ FAB Click → CreatePost
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}
