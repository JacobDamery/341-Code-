package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AvatarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        findViewById(R.id.avatar_back_arrow).setOnClickListener(view -> {
            Intent intent = new Intent(AvatarActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}