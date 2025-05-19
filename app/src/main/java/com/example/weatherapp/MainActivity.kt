package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout for this activity

        // Find the UI elements by their IDs
        val appNameTextView = findViewById<TextView>(R.id.appName)
        val nameStudentTextView = findViewById<TextView>(R.id.studentName)
        val viewForecastButton = findViewById<Button>(R.id.forecastbutton)

        // Set the text for the UI elements
        appNameTextView.text = "Cape Weather"
        nameStudentTextView.text = "Dalian Matsika ST10492347"


        // Set a click listener for the button to navigate to the ForecastActivity
        viewForecastButton.setOnClickListener {
            val intent = Intent(this,MainScreenActivity::class.java)
            startActivity(intent) // Start the new activity
        }
    }
}