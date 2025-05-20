package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainScreenActivity  : AppCompatActivity() {

    @SuppressLint("DefaultLocale", "SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen) // Set the layout for this activity

        // Find the UI elements by their IDs
        val forecastTextView = findViewById<TextView>(R.id.forecast_text_view)
        val averageTempTextView = findViewById<TextView>(R.id.average_temp_text_view)
        val backButton = findViewById<Button>(R.id.back_button)

        // Hardcoded data for days and temperatures
        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val maxTemps = arrayOf(25, 29, 22, 24, 20, 18, 16)

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

        backButton.setOnClickListener {
            val intent = Intent(this,DataScreenActivity::class.java)
            startActivity(intent) // Start the new activity
        }
    }
}