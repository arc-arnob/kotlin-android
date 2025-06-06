package com.arnob.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    val name: String,
    val phone: String,
    val email: String
)