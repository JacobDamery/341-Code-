package com.example.stiveworkoutapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Get the user profile data from the intent
        String name = getIntent().getStringExtra("name");
        String bio = getIntent().getStringExtra("bio");
        int steps = getIntent().getIntExtra("steps", 0);
        int sleepHours = getIntent().getIntExtra("sleepHours", 0);
        int caloriesBurned = getIntent().getIntExtra("caloriesBurned", 0);

        // Set the data to the views
        TextView tvName = findViewById(R.id.username_text);
        TextView tvSteps = findViewById(R.id.steps_text);
        TextView tvSleep = findViewById(R.id.sleep_text);
        TextView tvCalories = findViewById(R.id.calories_text);

        tvName.setText(name);
        tvSteps.setText("Steps: " + steps);
        tvSleep.setText("Sleep: " + sleepHours + " hours");
        tvCalories.setText("Calories: " + caloriesBurned);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account_feed)
                .setupBottomNavigation(bottomNav);
                
        // Setup back arrow functionality
        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(view -> {
            // Go back to previous activity
            finish();
        });
    }
}
