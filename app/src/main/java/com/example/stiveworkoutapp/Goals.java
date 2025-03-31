package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Goals extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_goals)
                .setupBottomNavigation(bottomNav);

        // ✅ Hook up FAB to launch CreatePost activity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0); // optional: disable transition animation
        });

        // ✅ Find Views for Steps and Sleep
        EditText etSteps = findViewById(R.id.etMetricValue);
        TextView tvStepsInfo = findViewById(R.id.tvMetricGoal);

        EditText etSleep = findViewById(R.id.etSleepValue);
        TextView tvSleepInfo = findViewById(R.id.tvSleepGoal);
        EditText etCalories = findViewById(R.id.etCalorieValue);
        TextView tvCaloriesInfo = findViewById(R.id.tvCalorieGoal);

        // ✅ Update steps text dynamically
        etSteps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String steps = s.toString().isEmpty() ? "0" : s.toString();
                tvStepsInfo.setText("You've taken " + steps + " steps out of 20,000.");
            }
        });

        // ✅ Update sleep text dynamically
        etSleep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String sleep = s.toString().isEmpty() ? "0" : s.toString();
                tvSleepInfo.setText("You've slept " + sleep + " hours out of 8.");
            }
        });
        etSleep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String Calorie = s.toString().isEmpty() ? "0" : s.toString();
                tvSleepInfo.setText("You've slept " + Calorie + " hours out of 8.");
            }
        });
    }
}
