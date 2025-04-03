package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FriendsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);

        // Setup bottom navigation
        setSupportActionBar(findViewById(R.id.bottom_app_bar));
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account_feed)
                .setupBottomNavigation(bottomNav);

        // Setup FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        List<Friend> friends = Arrays.asList(
                new Friend("Sami"),
                new Friend("Ryan"),
                new Friend("Joaquin"),
                new Friend("Jacob")
        );

        // Set adapter
        FriendsAdapter adapter = new FriendsAdapter(friends);
        recyclerView.setAdapter(adapter);

        Button forYouButton = findViewById(R.id.for_you_button);
        Button followingButton = findViewById(R.id.following_button);

        forYouButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AccountFeed.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        followingButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AccountFeed.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Friends is active, no click handler needed
    }

    private void setupNavigation() {
        TextView forYouText = findViewById(R.id.for_you_text);
        TextView followingText = findViewById(R.id.following_text);
        TextView friendsText = findViewById(R.id.friends_text);

        forYouText.setOnClickListener(v -> {
            Intent intent = new Intent(this, AccountFeed.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        followingText.setOnClickListener(v -> {
            Intent intent = new Intent(this, AccountFeed.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Friends text is already active, no need for click handler
    }
}