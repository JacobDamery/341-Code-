package com.example.stiveworkoutapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreatePost extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        // ✅ Attach FAB to BottomAppBar (enables cradle behavior)
        setSupportActionBar(findViewById(R.id.bottom_app_bar));

        // ✅ Setup Bottom Navigation (no item selected)
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, -1) // -1 means no nav item is selected
                .setupBottomNavigation(bottomNav);

        // ✅ Optional FAB setup (might do nothing on this page)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // You can leave this blank or maybe show a toast:
            // Toast.makeText(this, "Already on CreatePost", Toast.LENGTH_SHORT).show();
        });
    }
}
