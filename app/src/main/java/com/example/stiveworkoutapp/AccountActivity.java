package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView accountPostsRecyclerView;
    private PostsAdapter accountPostsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        // Bottom nav + fab
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account).setupBottomNavigation(bottomNav);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreatePost.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // Settings & Friends
        findViewById(R.id.settings_icon).setOnClickListener(view -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.friends_icon).setOnClickListener(view -> {
            startActivity(new Intent(this, FriendsList.class));
        });

        // ✅ Setup posts grid under "Your posts"
        accountPostsRecyclerView = findViewById(R.id.accountPostsRecyclerView);
        accountPostsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        accountPostsAdapter = new PostsAdapter(new ArrayList<>());
        accountPostsRecyclerView.setAdapter(accountPostsAdapter);

        // Initial load
        refreshAccountPosts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAccountPosts();
    }

    private void refreshAccountPosts() {
        List<Post> allPosts = new ArrayList<>();

        // Add user-created posts first (most recent first)
        allPosts.addAll(PostRepository.getAllPosts());

        // Then dummy posts
        allPosts.add(new Post(R.drawable.post1));
        allPosts.add(new Post(R.drawable.post2));
        allPosts.add(new Post(R.drawable.post3));

        accountPostsAdapter.updatePosts(allPosts);
    }

    public void openPostsActivity(View view) {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }
}
