package com.example.stiveworkoutapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Bottom Nav Bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val bottomNavigationHandler = BottomNavigationHandler(this, R.id.nav_home)
        bottomNavigationHandler.setupBottomNavigation(bottomNavigationView)

        // ✅ FAB Click to Launch CreatePost
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, CreatePost::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}
