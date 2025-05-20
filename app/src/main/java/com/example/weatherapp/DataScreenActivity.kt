package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DataScreenActivity : AppCompatActivity() {

    // Arrays to hold the weather data. We'll receive these from the previous activity.
    private lateinit var days: Array<String>
    private lateinit var maxTemps: IntArray // Use IntArray for primitive int arrays
    private lateinit var weatherConditions: Array<String>

    // UI elements
    private lateinit var dayInputEditText: EditText
    private lateinit var tempInputEditText: EditText
    private lateinit var conditionInputEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var displayTextView: TextView

    @SuppressLint("MissingInflatedId") // This annotation suppresses warnings for missing IDs,
    // but ensure your activity_datascreen.xml has all necessary IDs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datascreen) // Make sure this layout file exists and has the IDs

        // Initialize UI elements by finding them from the layout
        dayInputEditText = findViewById(R.id.dayInputEditText)
        tempInputEditText = findViewById(R.id.tempInputEditText)
        conditionInputEditText = findViewById(R.id.conditionInputEditText)
        saveButton = findViewById(R.id.saveButton)
        displayTextView = findViewById(R.id.displayTextView) // This TextView will show the current data

        // Retrieve data passed from the MainScreenActivity via the Intent
        // Use default empty arrays if no data is passed (e.g., if navigating directly)
        days = intent.getStringArrayExtra("days") ?: arrayOf()
        maxTemps = intent.getIntArrayExtra("maxTemps") ?: intArrayOf() // Correctly handle IntArray
        weatherConditions = intent.getStringArrayExtra("weatherConditions") ?: arrayOf()

        // Set up the click listener for the save button
        saveButton.setOnClickListener {
            saveData()
        }

        // Display the current data when the activity is created
        displayData()
    }

    /**
     * Handles saving the data entered by the user.
     * Validates input and updates the arrays, then navigates back to MainScreenActivity.
     */
    private fun saveData() {
        val day = dayInputEditText.text.toString().trim()
        val tempStr = tempInputEditText.text.toString().trim()
        val condition = conditionInputEditText.text.toString().trim()

        // Input validation: Check if any field is empty
        if (day.isEmpty() || tempStr.isEmpty() || condition.isEmpty()) { // Fixed logical OR
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            return // Stop execution if validation fails
        }

        // Convert temperature string to an integer, handling potential errors
        val temp = tempStr.toIntOrNull()
        if (temp == null) {
            Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
            return // Stop execution if temperature is not a valid number
        }

        // Find the index of the entered day in our 'days' array
        val dayIndex = days.indexOf(day)
        if (dayIndex != -1) {
            // If the day is found, update the corresponding values in the arrays
            maxTemps[dayIndex] = temp
            weatherConditions[dayIndex] = condition
            Toast.makeText(this, "Data updated for $day", Toast.LENGTH_SHORT).show()
        } else {
            // If the day is not found in our predefined list of days
            Toast.makeText(this, "Day not found: $day. Please enter a valid day (e.g., Monday).", Toast.LENGTH_LONG).show()
            return // Stop execution if day is not found
        }

        // Clear the input fields after successful data saving
        dayInputEditText.text.clear()
        tempInputEditText.text.clear()
        conditionInputEditText.text.clear()

        // Update the displayTextView with the newly saved data
        displayData()

        // Navigate back to MainScreenActivity
        val intent = Intent(this, MainScreenActivity::class.java)
        // Pass the updated arrays back to MainScreenActivity
        intent.putExtra("days", days)
        intent.putExtra("maxTemps", maxTemps)
        intent.putExtra("weatherConditions", weatherConditions)

        startActivity(intent) // Start the MainScreenActivity
        finish() // Close DataChangeActivity so it's removed from the back stack
    }

    /**
     * Updates the displayTextView with the current weather data from the arrays.
     */
    private fun displayData() {
        var displayText = "Current Data:\n"
        // Ensure arrays are not empty before attempting to iterate
        if (days.isNotEmpty() && maxTemps.isNotEmpty() && weatherConditions.isNotEmpty()) {
            for (i in days.indices) {
                // Check if index is within bounds for all arrays
                if (i < maxTemps.size && i < weatherConditions.size) {
                    displayText += "${days[i]}: ${maxTemps[i]}°C, ${weatherConditions[i]}\n"
                }
            }
        } else {
            displayText += "No data available."
        }
        displayTextView.text = displayText
    }
}