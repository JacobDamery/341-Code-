package com.example.stiveworkoutapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    private List<Comment> comments;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_activity);

        // Initialize comments list with dummy data
        comments = new ArrayList<>();
        comments.add(new Comment("JohnFit", "Looking strong! 💪"));
        comments.add(new Comment("WorkoutPro", "Great form on those exercises!"));
        comments.add(new Comment("FitnessGuru", "Keep pushing yourself! 🔥"));
        comments.add(new Comment("GymBuddy", "This is inspiring!"));
        comments.add(new Comment("HealthyLife", "Amazing progress!"));

        // Set up RecyclerView
        RecyclerView commentsRecyclerView = findViewById(R.id.comments_recycler_view);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentsAdapter(comments);
        commentsRecyclerView.setAdapter(adapter);

        // Set up bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        new BottomNavigationHandler(this, R.id.nav_account_feed)
                .setupBottomNavigation(bottomNav);

        // Set up input and button
        EditText commentInput = findViewById(R.id.comment_input);
        Button addCommentButton = findViewById(R.id.add_comment_button);

        addCommentButton.setOnClickListener(v -> {
            String newCommentText = commentInput.getText().toString().trim();
            if (!newCommentText.isEmpty()) {
                Comment newComment = new Comment("You", newCommentText);
                comments.add(newComment);
                adapter.notifyItemInserted(comments.size() - 1);
                commentsRecyclerView.scrollToPosition(comments.size() - 1);
                commentInput.setText("");
            }
        });
    }
}