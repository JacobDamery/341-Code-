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
<<<<<<< Updated upstream
                String steps = s.toString().isEmpty() ? "0" : s.toString();
                tvStepsInfo.setText("You've taken " + steps + " steps out of 20,000.");
=======
                String stepsStr = s.toString().isEmpty() ? "0" : s.toString();
                try {
                    int steps = Integer.parseInt(stepsStr);
                    if (steps > 100000) {
                        etSteps.setError("Too many steps! Enter a value less than 100,000.");
                        return;
                    }
                    tvStepsInfo.setText("You've taken " + steps + " steps out of 20,000.");
                    saveDailyData();
                } catch (NumberFormatException e) {
                    etSteps.setError("Invalid number");
                }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                String sleep = s.toString().isEmpty() ? "0" : s.toString();
                tvSleepInfo.setText("You've slept " + sleep + " hours out of 8.");
=======
                String sleepStr = s.toString().isEmpty() ? "0" : s.toString();
                try {
                    int sleep = Integer.parseInt(sleepStr);
                    if (sleep > 24) {
                        etSleep.setError("You can't sleep more than 24 hours in a day!");
                        return;
                    }
                    tvSleepInfo.setText("You've slept " + sleep + " hours out of 8.");
                    saveDailyData();
                } catch (NumberFormatException e) {
                    etSleep.setError("Invalid number");
                }
>>>>>>> Stashed changes
            }

        });
        etCalories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
<<<<<<< Updated upstream
                String Calorie = s.toString().isEmpty() ? "0" : s.toString();
                tvCaloriesInfo.setText("You've burned " + Calorie + "  out of 2000.");
=======
                String calorieStr = s.toString().isEmpty() ? "0" : s.toString();
                try {
                    int calories = Integer.parseInt(calorieStr);
                    if (calories > 1000000) {
                        etCalories.setError("That's too many calories burned! Try under 1,000,000.");
                        return;
                    }
                    tvCaloriesInfo.setText("You've burned " + calories + " out of 2000.");
                    saveDailyData();
                } catch (NumberFormatException e) {
                    etCalories.setError("Invalid number");
                }
>>>>>>> Stashed changes
            }

        });
    }
}
