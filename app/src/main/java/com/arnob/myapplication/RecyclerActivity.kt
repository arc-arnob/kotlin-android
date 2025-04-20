package com.arnob.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnob.myapplication.databinding.ActivityRecyclerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var db: AppDatabase
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database
        db = AppDatabase.getDatabase(this)
        val repository = ContactRepository(db.contactDao())

        // Initialize the ViewModel using a Factory
        val factory = ContactViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ContactViewModel::class.java]

        // Set up RecyclerView
        setupRecyclerView()


        // Set up flow collection
        observeContacts()

        // Button to add a contact
        binding.fabAddContact.setOnClickListener {
            addNewContact()
        }
    }
    private fun observeContacts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contacts.collect { contacts ->
                    // UPDATE the existing adapter instead of creating a new one
                    contactAdapter.updateContacts(contacts)
                }
            }
        }
    }
    private fun setupRecyclerView() {
        // Initialize adapter with empty list
        contactAdapter = ContactAdapter(emptyList()) { contact ->
            Toast.makeText(this, "Selected: ${contact.name}", Toast.LENGTH_SHORT).show()
        }
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = contactAdapter

        // Observe contacts from ViewModel
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contacts.collect { contacts ->
                    contactAdapter = ContactAdapter(contacts) { contact ->
                        Toast.makeText(this@RecyclerActivity,
                            "Selected: ${contact.name}",
                            Toast.LENGTH_SHORT).show()
                    }
                    binding.contactsRecyclerView.adapter = contactAdapter
                }
            }
        }
    }

    private fun addNewContact() {
        // Create a new contact
        val newContact = Contact(
            name = "New Contact ${System.currentTimeMillis() % 1000}",
            phone = "555-${(1000..9999).random()}",
            email = "new${(100..999).random()}@example.com"
        )

        // Add it using the ViewModel
        viewModel.addContact(newContact)
    }
}

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}