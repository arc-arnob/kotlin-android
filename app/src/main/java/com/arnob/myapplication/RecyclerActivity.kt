package com.arnob.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnob.myapplication.databinding.ActivityRecyclerBinding


class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create sample data
        val contacts = listOf(
            Contact(1, "John Smith", "555-1234", "john.smith@example.com"),
            Contact(2, "Jane Doe", "555-5678", "jane.doe@example.com"),
            Contact(3, "Bob Johnson", "555-8765", "bob.johnson@example.com"),
            Contact(4, "Alice Brown", "555-4321", "alice.brown@example.com"),
            Contact(5, "Mike Wilson", "555-9876", "mike.wilson@example.com")
        )

        // Set up RecyclerView
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = ContactAdapter(contacts) { contact ->
            // Handle click on contact
            Toast.makeText(
                this,
                "Selected: ${contact.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}