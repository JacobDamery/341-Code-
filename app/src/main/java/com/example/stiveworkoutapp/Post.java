package com.example.stiveworkoutapp;

public class Post {
    private String username;
    private String caption;
    private int imageResId;
    private long timestamp;

    public Post(String username, String caption, int imageResId) {
        this.username = username;
        this.caption = caption;
        this.imageResId = imageResId;
        this.timestamp = System.currentTimeMillis();
    }

    public String getUsername() {
        return username;
    }

    public String getCaption() {
        return caption;
    }

    public int getImageResId() {
        return imageResId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
