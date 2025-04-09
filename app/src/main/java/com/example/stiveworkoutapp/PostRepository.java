package com.example.stiveworkoutapp;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private static final List<Post> postList = new ArrayList<>();

    public static void addPost(Post post) {
        postList.add(0, post); // Add at the top (newest first)
    }

    public static List<Post> getAllPosts() {
        return new ArrayList<>(postList); // Return copy to avoid direct modification
    }

    public static void clearPosts() {
        postList.clear();
    }
}
