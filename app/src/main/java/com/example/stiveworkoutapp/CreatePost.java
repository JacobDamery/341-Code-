package com.example.stiveworkoutapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreatePost extends AppCompatActivity {

    private EditText descriptionInput;
    private SwitchCompat publicSwitch;
    private SwitchCompat commentsSwitch;
    private Button createPostButton;
    private ImageButton backButton;
    private ImageView workoutImage;
    private LinearLayout addPhotoOption;
    private LinearLayout tagPeopleOption;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        workoutImage.setImageURI(selectedImageUri);
                        Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        setSupportActionBar(findViewById(R.id.bottom_app_bar));
        initializeViews();
        setupClickListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        descriptionInput = findViewById(R.id.description_input);
        publicSwitch = findViewById(R.id.public_switch);
        commentsSwitch = findViewById(R.id.comments_switch);
        createPostButton = findViewById(R.id.create_post_button);
        backButton = findViewById(R.id.back_button);
        workoutImage = findViewById(R.id.workout_image);
        addPhotoOption = findViewById(R.id.add_photo_option);
        tagPeopleOption = findViewById(R.id.tag_people_option);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(view -> finish());

        addPhotoOption.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        tagPeopleOption.setOnClickListener(view -> {
            Toast.makeText(this, "Tag people clicked", Toast.LENGTH_SHORT).show();
        });

        createPostButton.setOnClickListener(view -> {
            String description = descriptionInput.getText().toString();
            boolean isPublic = publicSwitch.isChecked();
            boolean allowComments = commentsSwitch.isChecked();

            if (description.isEmpty()) {
                Toast.makeText(this, "Please write a description", Toast.LENGTH_SHORT).show();
                return;
            }

            createPost(description, isPublic, allowComments);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(view -> {
                Toast.makeText(this, "FAB clicked (you're already here)", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void createPost(String description, boolean isPublic, boolean allowComments) {
        createPostButton.setEnabled(false);
        createPostButton.setText("Creating...");

        createPostButton.postDelayed(() -> {
            Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(0, 0);
        }, 1500);
    }

    private void setupBottomNavigation() {
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        if (bottomAppBar != null) {
            setSupportActionBar(bottomAppBar);
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        if (bottomNav != null) {
            new BottomNavigationHandler(this, R.id.nav_account_feed)
                    .setupBottomNavigation(bottomNav);
        }
    }
}