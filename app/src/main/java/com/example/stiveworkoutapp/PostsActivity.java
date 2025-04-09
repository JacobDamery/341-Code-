package com.example.stiveworkoutapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stiveworkoutapp.Post;
import com.example.stiveworkoutapp.PostsAdapter;
import com.example.stiveworkoutapp.R;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView postsRecyclerView;
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_activity);

        postsRecyclerView = findViewById(R.id.postsRecyclerView);

        // Set GridLayoutManager with 3 columns
        postsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        // dummy posts
        List<Post> allPosts = new ArrayList<>();

// Add dummy posts
        allPosts.addAll(PostRepository.getAllPosts());
        allPosts.add(new Post(R.drawable.post1));
        allPosts.add(new Post(R.drawable.post2));
        allPosts.add(new Post(R.drawable.post3));

// Add user-created posts from the repository


// Initialize adapter and set to RecyclerView
        postsAdapter = new PostsAdapter(allPosts);
        postsRecyclerView.setAdapter(postsAdapter);

    }

    public void goBackToProfile(View view) {
        // Finish the current activity to go back to the previous screen (Profile)
        finish();
    }
}