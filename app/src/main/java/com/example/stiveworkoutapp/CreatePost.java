package com.example.stiveworkoutapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class CreatePost extends AppCompatActivity {

    private EditText descriptionInput;
    private SwitchCompat publicSwitch;
    private SwitchCompat commentsSwitch;
    private Button createPostButton;
    private ImageButton backButton;
    private ImageView workoutImage;

    private Uri savedImageUri = null;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        Uri copiedUri = copyImageToInternalStorage(selectedImageUri);
                        if (copiedUri != null) {
                            savedImageUri = copiedUri;
                            workoutImage.setImageURI(copiedUri);
                            Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed to copy image", Toast.LENGTH_SHORT).show();
                        }
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
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(view -> finish());

        findViewById(R.id.add_photo_option).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        findViewById(R.id.tag_people_option).setOnClickListener(view -> {
            Toast.makeText(this, "Tag people clicked", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.add_location_option).setOnClickListener(view -> {
            Toast.makeText(this, "Add location clicked", Toast.LENGTH_SHORT).show();
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
        fab.setOnClickListener(view -> {
            Toast.makeText(this, "FAB clicked (you’re already here)", Toast.LENGTH_SHORT).show();
        });
    }

    private void createPost(String description, boolean isPublic, boolean allowComments) {
        createPostButton.setEnabled(false);
        createPostButton.setText("Creating...");

        workoutImage.postDelayed(() -> {
            if (savedImageUri != null) {
                Post newPost = new Post(savedImageUri);
                PostRepository.addPost(newPost);
            } else {
                Post fallbackPost = new Post(R.drawable.testimage);
                PostRepository.addPost(fallbackPost);
            }

            Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(0, 0);
        }, 1000);
    }

    private Uri copyImageToInternalStorage(Uri sourceUri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream inputStream = resolver.openInputStream(sourceUri);

            if (inputStream != null) {
                File imageFile = new File(getFilesDir(), "post_" + UUID.randomUUID() + ".jpg");
                FileOutputStream outputStream = new FileOutputStream(imageFile);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                inputStream.close();
                outputStream.close();

                return Uri.fromFile(imageFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
