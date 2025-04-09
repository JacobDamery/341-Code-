package com.example.stiveworkoutapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private static final String TAG = "AccountActivity";
    private static final String PREFS_NAME = "user_posts_prefs";
    private static final String KEY_POST_URIS = "post_uris";

    private UserLevel userLevel;
    private List<String> imageUris = new ArrayList<>();
    private ImageView[] postImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        userLevel = new UserLevel(this);

        TextView lvlText = findViewById(R.id.lvl_text);
        lvlText.setText(userLevel.getLevel() + "\nlvl");

        postImageViews = new ImageView[]{
                findViewById(R.id.post_image_1),
                findViewById(R.id.post_image_2),
                findViewById(R.id.post_image_3)
        };

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account).setupBottomNavigation(bottomNav);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        findViewById(R.id.settings_icon).setOnClickListener(view ->
                startActivity(new Intent(this, SettingsActivity.class)));

        findViewById(R.id.friends_icon).setOnClickListener(view ->
                startActivity(new Intent(this, FriendsList.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView lvlText = findViewById(R.id.lvl_text);
        lvlText.setText(userLevel.getLevel() + "\nlvl");

        // Load images
        imageUris = loadImageUris();
        Log.d(TAG, "Loaded image URIs: " + imageUris);

        // Check if CreatePost passed a new image
        String newUri = getIntent().getStringExtra("new_post_uri");
        if (newUri != null && !newUri.isEmpty()) {
            Log.d(TAG, "Received new post URI: " + newUri);
            addNewPostImage(newUri);
            getIntent().removeExtra("new_post_uri");
        }

        updateImageViews();
    }

    private void addNewPostImage(String uri) {
        imageUris.add(0, uri);
        if (imageUris.size() > 3) {
            imageUris = imageUris.subList(0, 3);
        }
        saveImageUris(imageUris);
    }

    private void updateImageViews() {
        for (int i = 0; i < 3; i++) {
            if (i < imageUris.size()) {
                try {
                    Uri uri = Uri.parse(imageUris.get(i));
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    if (inputStream != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        postImageViews[i].setImageBitmap(bitmap);
                        inputStream.close();
                        Log.d(TAG, "Loaded image " + (i + 1) + ": " + uri);
                    } else {
                        throw new Exception("Input stream was null");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed to load image: " + e.getMessage());
                    postImageViews[i].setImageResource(R.drawable.post1);
                }
            } else {
                postImageViews[i].setImageResource(R.drawable.post2);
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
