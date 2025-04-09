package com.example.stiveworkoutapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        public class AccountActivity extends AppCompatActivity {
            private UserLevel userLevel;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.account_activity);
                userLevel = new UserLevel(this);

                // Update the level display
                TextView lvlText = findViewById(R.id.lvl_text);
                lvlText.setText(userLevel.getLevel() + "\nlvl");

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
                });

                // Friends button click handler
                findViewById(R.id.friends_icon).setOnClickListener(view -> {
                    Intent intent = new Intent(this, FriendsList.class);
                    startActivity(intent);
                });
            }

            public void openPostsActivity(View view) {
                Intent intent = new Intent(this, PostsActivity.class);
                startActivity(intent);
            }

            @Override
            protected void onResume() {
                super.onResume();
                // Update level display when returning to the activity
                TextView lvlText = findViewById(R.id.lvl_text);
                lvlText.setText(userLevel.getLevel() + "\nlvl");
            }
        }
