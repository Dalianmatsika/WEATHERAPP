package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout for this activity

        // Find the UI elements by their IDs
        val appNameTextView = findViewById<TextView>(R.id.app_name_text_view)
        val nameStudentTextView = findViewById<TextView>(R.id.name_student_text_view)
        val viewForecastButton = findViewById<Button>(R.id.view_forecast_button)
        val imageView = 

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