package com.example.stiveworkoutapp;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.activity.result.ActivityResultLauncher;
        import androidx.activity.result.contract.ActivityResultContracts;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.io.IOException;

        public class AccountActivity extends AppCompatActivity {
            private UserLevel userLevel;
            private static final int AVATAR_REQUEST_CODE = 100;
            private ImageView profileImageView;
            
            // Activity result launcher for avatar selection
            private final ActivityResultLauncher<Intent> avatarLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Avatar was changed, reload the profile image
                        loadProfileImage();
                    }
                }
            );

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.account_activity);
                userLevel = new UserLevel(this);

                // Initialize profile image view
                profileImageView = findViewById(R.id.profile_icon);
                
                // Set click listener on profile image to open AvatarActivity
                profileImageView.setOnClickListener(v -> {
                    Intent intent = new Intent(AccountActivity.this, AvatarActivity.class);
                    avatarLauncher.launch(intent);
                });
                
                // Load the saved avatar
                loadProfileImage();

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

            private void loadProfileImage() {
                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                boolean isCustomImage = sharedPreferences.getBoolean("isCustomImage", false);
                
                if (isCustomImage) {
                    // Load custom image if one is selected
                    String customImagePath = sharedPreferences.getString("customImagePath", null);
                    if (customImagePath != null) {
                        try {
                            Uri imageUri = Uri.parse(customImagePath);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                            profileImageView.setImageBitmap(bitmap);
                        } catch (IOException | SecurityException e) {
                            // If there's an error loading the custom image, fall back to default
                            profileImageView.setImageResource(R.drawable.profile_icon);
                            e.printStackTrace();
                        }
                    }
                } else {
                    // Load selected default avatar
                    int avatarIndex = sharedPreferences.getInt("avatarIndex", 0);
                    int avatarResourceId = sharedPreferences.getInt("avatarResourceId", R.drawable.profile_icon);
                    profileImageView.setImageResource(avatarResourceId);
                }
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
                
                // Reload profile image in case it was changed
                loadProfileImage();
            }
        }
    }


    private void saveImageUris(List<String> uris) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String joined = String.join("||", uris); // safer delimiter
        prefs.edit().putString(KEY_POST_URIS, joined).apply();
        Log.d(TAG, "Saved URIs: " + joined);
    }

    private List<String> loadImageUris() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String saved = prefs.getString(KEY_POST_URIS, "");
        if (saved.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(saved.split("\\|\\|")));
    }

    public void openPostsActivity(android.view.View view) {
        startActivity(new Intent(this, PostsActivity.class));
    }
}
