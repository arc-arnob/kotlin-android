package com.arnob.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arnob.myapplication.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from MainActivity
        val name = intent.getStringExtra("NAME_KEY") ?: "Guest"

        // Display the data
        binding.textViewGreeting.text = "Welcome, $name!"

        // Set up a button to go back
        binding.buttonBack.setOnClickListener {
            // Simply finish this activity to go back
            finish()
        }
    }
}