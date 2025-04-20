package com.arnob.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {
    // Expose contacts as LiveData or StateFlow
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList()) // Mutable state, internal to ViewModel
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow() // Public read-only state for the UI to observe

    init {
        // Collect repository flow and update the UI state
        viewModelScope.launch {
            repository.getAllContacts().collect { contactList ->
                _contacts.value = contactList
            }
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}