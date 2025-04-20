package com.arnob.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnob.myapplication.databinding.ActivityRecyclerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Room DB for local data

RecyclerView for UI list

View Binding

Coroutines for background work
* */

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var db: AppDatabase
    private lateinit var contactAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database
        db = AppDatabase.getDatabase(this)

//        // Create sample data
//        val contacts = listOf(
//            Contact(1, "John Smith", "555-1234", "john.smith@example.com"),
//            Contact(2, "Jane Doe", "555-5678", "jane.doe@example.com"),
//            Contact(3, "Bob Johnson", "555-8765", "bob.johnson@example.com"),
//            Contact(4, "Alice Brown", "555-4321", "alice.brown@example.com"),
//            Contact(5, "Mike Wilson", "555-9876", "mike.wilson@example.com")
//        )
//
//        // Set up RecyclerView
//        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
//        binding.contactsRecyclerView.adapter = ContactAdapter(contacts) { contact ->
//            // Handle click on contact
//            Toast.makeText(
//                this,
//                "Selected: ${contact.name}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
        // Set up your existing RecyclerView
        setupRecyclerView()

        // Load contacts from database
        loadContacts()

    }
    private fun setupRecyclerView() {
        // Your existing RecyclerView setup code
        contactAdapter = ContactAdapter(emptyList()) { contact ->
            Toast.makeText(this, "Selected: ${contact.name}", Toast.LENGTH_SHORT).show()
        }
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = contactAdapter
    }

    private fun loadContacts() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Get contacts from database
            val contacts = db.contactDao().getAllContacts()

            // If database is empty, add some sample data
            if (contacts.isEmpty()) {
                addSampleContacts()
                val updatedContacts = db.contactDao().getAllContacts()
                updateUI(updatedContacts)
            } else {
                updateUI(contacts)
            }
        }
    }
    private fun updateUI(contacts: List<Contact>) {
        lifecycleScope.launch(Dispatchers.Main) {
            contactAdapter = ContactAdapter(contacts) { contact ->
                Toast.makeText(this@RecyclerActivity,
                    "Selected: ${contact.name}",
                    Toast.LENGTH_SHORT).show()
            }
            binding.contactsRecyclerView.adapter = contactAdapter
        }
    }

    private fun addSampleContacts() {
        val sampleContacts = listOf(
            Contact(name = "John Smith", phone = "555-1234", email = "john.smith@example.com"),
            Contact(name = "Jane Doe", phone = "555-5678", email = "jane.doe@example.com"),
            Contact(name = "Bob Johnson", phone = "555-8765", email = "bob.johnson@example.com")
        )

        sampleContacts.forEach { contact ->
            db.contactDao().insertContact(contact)
        }
    }
}