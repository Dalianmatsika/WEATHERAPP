package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainScreenActivity  : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen) // Set the layout for this activity

        // Find the UI elements by their IDs
        val forecastTextView = findViewById<TextView>(R.id.forecasttextview)
        val averageTempTextView = findViewById<TextView>(R.id.averagetemptextview)
        val backButton = findViewById<Button>(R.id.backbutton)

        // Hardcoded data for days and temperatures
        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val maxTemps = arrayOf(20, 23, 22, 27, 29, 14, 17)

        // Build the forecast string using a loop
        var forecastText = "Daily Forecast:\n"
        for (i in days.indices) {
            forecastText += "${days[i]}: ${maxTemps[i]}°C\n"
        }
        forecastTextView.text = forecastText

        // Calculate the average temperature using a loop
        var totalTemp = 0
        for (temp in maxTemps) {
            totalTemp += temp
        }
        val averageTemp = totalTemp.toDouble() / maxTemps.size
        averageTempTextView.text = "Average Max Temperature: ${String.format("%.2f", averageTemp)}°C"

        // Set a click listener for the back button to finish this activity and go back
        backButton.setOnClickListener {
            finish() // Close this activity and go back to the previous one
        }
    }
}