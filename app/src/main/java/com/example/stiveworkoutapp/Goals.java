package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Goals extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        // ✅ Attach BottomAppBar to enable FAB cradle
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);

        // ✅ Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_goals)
                .setupBottomNavigation(bottomNav);

        // ✅ FAB click → CreatePost
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
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
        etCalories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String Calorie = s.toString().isEmpty() ? "0" : s.toString();
                tvCaloriesInfo.setText("You've burned " + Calorie + "  out of 2000.");
            }
        });
    }
}
