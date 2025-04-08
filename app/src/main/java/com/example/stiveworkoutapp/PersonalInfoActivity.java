package com.example.stiveworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        findViewById(R.id.personal_info_back_arrow).setOnClickListener(view -> {
        Intent intent = new Intent(PersonalInfoActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}