package com.example.stiveworkoutapp;

public class Friend {
    private String username;
    private int profileImageResId;

    public Friend(String username, int profileImageResId) {
        this.username = username;
        this.profileImageResId = profileImageResId;
    }

    public String getUsername() {
        return username;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }
}
