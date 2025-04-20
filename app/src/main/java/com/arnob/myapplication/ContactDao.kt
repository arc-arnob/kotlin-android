package com.arnob.myapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts")
    fun getAllContactsFlow(): Flow<List<Contact>>  // Using Kotlin Flow

    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContactById(contactId: Long): Contact

    @Insert
    fun insertContact(contact: Contact): Long

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}