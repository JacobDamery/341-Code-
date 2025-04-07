package com.example.stiveworkoutapp

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "DailyGoalsPrefs"

    // YouTube links - replace with your actual links
    private val UPPERBODY_LINK = "https://youtube.com/your_upperbody_video"
    private val CARDIO_LINK = "https://youtube.com/your_cardio_video"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val bottomAppBar = findViewById<com.google.android.material.bottomappbar.BottomAppBar>(R.id.bottom_app_bar)

        setSupportActionBar(bottomAppBar)

        fab.setOnClickListener {
            val intent = Intent(this, CreatePost::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BottomNavigationHandler(this, R.id.nav_home).setupBottomNavigation(bottomNavigationView)

        // Set click listeners for workout buttons
        findViewById<androidx.cardview.widget.CardView>(R.id.btnUpperbody).setOnClickListener {
            openYouTubeVideo(UPPERBODY_LINK)
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.btnCardio).setOnClickListener {
            openYouTubeVideo(CARDIO_LINK)
        }

        // Load goals
        loadTodayGoals()
    }

    private fun openYouTubeVideo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.google.android.youtube")
        startActivity(intent)
    }

    private fun loadTodayGoals() {
        val dateKey = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())

        // Get today's goals from SharedPreferences
        val steps = sharedPreferences.getString("${dateKey}_steps", "0") ?: "0"
        val sleep = sharedPreferences.getString("${dateKey}_sleep", "0") ?: "0"
        val calories = sharedPreferences.getString("${dateKey}_calories", "0") ?: "0"

        // Update the TextViews
        findViewById<TextView>(R.id.tvStepsGoal).text = steps
        findViewById<TextView>(R.id.tvSleepGoal).text = formatSleepTime(sleep)
        findViewById<TextView>(R.id.tvCaloriesGoal).text = "$calories kcal"
    }

    private fun formatSleepTime(sleepHours: String): String {
        return try {
            val hours = sleepHours.toFloat()
            val wholeHours = hours.toInt()
            val minutes = ((hours - wholeHours) * 60).toInt()
            "${wholeHours}h ${minutes}m"
        } catch (e: NumberFormatException) {
            "0h 0m"
        }
    }
}