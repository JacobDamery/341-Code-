package com.example.stiveworkoutapp;

import android.net.Uri;

public class Post {
    private int imageResId = -1;
    private Uri imageUri = null;

    public Post(int imageResId) {
        this.imageResId = imageResId;
    }

    public Post(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public boolean isUriBased() {
        return imageUri != null;
    }

    public int getImageResId() {
        return imageResId;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
