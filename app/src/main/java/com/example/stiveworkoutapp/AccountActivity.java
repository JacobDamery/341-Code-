package com.example.stiveworkoutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    
    private List<Friend> friends;
    private Map<String, UserProfile> friendProfiles;
    private ImageView profileIconView;
    
    // Activity result launcher for avatar activity
    private final ActivityResultLauncher<Intent> avatarActivityLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK) {
                // Avatar was changed, reload the profile picture
                loadProfilePicture(profileIconView);
            }
        }
    );
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "username");
        String email = sharedPreferences.getString("email", "username@gmail.com");
        
        TextView usernameTextView = findViewById(R.id.username_text);
        TextView emailTextView = findViewById(R.id.gmail_text);
        profileIconView = findViewById(R.id.profile_icon);

        usernameTextView.setText(username);
        emailTextView.setText(email);
        
        // Load profile picture (custom or default)
        loadProfilePicture(profileIconView);
        
        // Initialize friend data
        initializeFriendData();
        
        // Setup top friends section
        setupTopFriends();

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
        
        // Allow profile icon to be clickable to change avatar
        profileIconView.setOnClickListener(view -> {
            Intent intent = new Intent(this, AvatarActivity.class);
            avatarActivityLauncher.launch(intent);
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Reload the profile picture in case it was changed
        loadProfilePicture(profileIconView);
    }
    
    private void initializeFriendData() {
        // Create the same friend data that's used in FriendsList
        friends = Arrays.asList(
                new Friend("Sami"),
                new Friend("Ryan"),
                new Friend("Joaquin"),
                new Friend("Jacob")
        );

        // Map friend names to profiles
        friendProfiles = new HashMap<>();
        friendProfiles.put("Sami", new UserProfile("Sami", "Loves hiking and fitness", 12000, 7, 1800));
        friendProfiles.put("Ryan", new UserProfile("Ryan", "Avid runner and cyclist", 15000, 6, 2000));
        friendProfiles.put("Joaquin", new UserProfile("Joaquin", "Night owl and gamer", 8000, 5, 1500));
        friendProfiles.put("Jacob", new UserProfile("Jacob", "Yoga and meditation enthusiast", 10000, 8, 1700));
    }
    
    private void setupTopFriends() {
        LinearLayout friendRow1 = findViewById(R.id.friend_row_1);
        LinearLayout friendRow2 = findViewById(R.id.friend_row_2);
        LinearLayout friendRow3 = findViewById(R.id.friend_row_3);
        
        // Get the 3 text views for friend names
        TextView friendName1 = friendRow1.findViewById(R.id.friend_name);
        TextView friendName2 = friendRow2.findViewById(R.id.friend_name);
        TextView friendName3 = friendRow3.findViewById(R.id.friend_name);
        
        // Set friend names (using first 3 friends from the list)
        if (friends.size() >= 1) {
            friendName1.setText(friends.get(0).getName());
        }
        if (friends.size() >= 2) {
            friendName2.setText(friends.get(1).getName());
        }
        if (friends.size() >= 3) {
            friendName3.setText(friends.get(2).getName());
        }
        
        // Set click listeners for each friend row
        friendRow1.setOnClickListener(view -> openFriendProfile(0));
        friendRow2.setOnClickListener(view -> openFriendProfile(1));
        friendRow3.setOnClickListener(view -> openFriendProfile(2));
    }
    
    private void openFriendProfile(int index) {
        if (index < 0 || index >= friends.size()) {
            return;
        }
        
        Friend friend = friends.get(index);
        UserProfile profile = friendProfiles.get(friend.getName());
        
        if (profile != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            intent.putExtra("name", profile.getName());
            intent.putExtra("bio", profile.getBio());
            intent.putExtra("steps", profile.getSteps());
            intent.putExtra("sleepHours", profile.getSleepHours());
            intent.putExtra("caloriesBurned", profile.getCaloriesBurned());
            startActivity(intent);
        }
    }
    
    private void loadProfilePicture(ImageView profileIconView) {
        // Get profile picture settings from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        boolean isCustomImage = sharedPreferences.getBoolean("isCustomImage", false);
        
        if (isCustomImage) {
            // Load custom image
            String customImagePath = sharedPreferences.getString("customImagePath", null);
            if (customImagePath != null) {
                try {
                    Uri profilePicUri = Uri.parse(customImagePath);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), profilePicUri);
                    profileIconView.setImageBitmap(bitmap);
                    return; // Successfully loaded custom image
                } catch (IOException e) {
                    e.printStackTrace();
                    // If loading fails, continue to use default avatar
                }
            }
        } else {
            // Load selected avatar from resources
            int avatarIndex = sharedPreferences.getInt("avatarIndex", 0);
            int avatarResourceId = sharedPreferences.getInt("avatarResourceId", R.drawable.profile_icon);
            profileIconView.setImageResource(avatarResourceId);
            return; // Successfully loaded avatar
        }
        
        // Fallback to default avatar if everything else fails
        profileIconView.setImageResource(R.drawable.profile_icon);
    }
    
    // Method for the "see all" posts button
    public void openPostsActivity(View view) {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }
}

