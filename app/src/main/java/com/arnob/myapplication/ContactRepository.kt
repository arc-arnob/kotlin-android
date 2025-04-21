package com.arnob.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class ContactRepository(private val contactDao: ContactDao) {
    // Using Flow for reactive updates
    fun getAllContacts(): Flow<List<Contact>> =
        contactDao.getAllContactsFlow().flowOn(Dispatchers.IO)

    suspend fun insertContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.insertContact(contact)
        }
    }

    suspend fun updateContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.updateContact(contact)
        }
    }

    suspend fun deleteContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.deleteContact(contact)
        }
    }
}