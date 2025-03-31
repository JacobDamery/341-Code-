package com.example.stiveworkoutapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class CreatePost extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        // No bottom navigation here — launched via FAB
    }
}
