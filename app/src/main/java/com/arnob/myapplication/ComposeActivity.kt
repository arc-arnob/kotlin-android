package com.arnob.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create sample data
        val contacts = listOf(
            Contact(1, "John Smith", "555-1234", "john.smith@example.com"),
            Contact(2, "Jane Doe", "555-5678", "jane.doe@example.com"),
            Contact(3, "Bob Johnson", "555-8765", "bob.johnson@example.com"),
            Contact(4, "Alice Brown", "555-4321", "alice.brown@example.com"),
            Contact(5, "Mike Wilson", "555-9876", "mike.wilson@example.com")
        )

        setContent {
            MaterialTheme {
                Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                    ContactList(contacts = contacts) { contact ->
                        Toast.makeText(
                            this,
                            "Selected: ${contact.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                   }
                }
            }
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>, onContactClick: (Contact) -> Unit) {
    LazyColumn {
        items(contacts) { contact ->
            ContactItem(contact = contact, onClick = { onContactClick(contact) })
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.phone,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.email,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    MaterialTheme {
        ContactItem(
            contact = Contact(1, "John Smith", "555-1234", "john@example.com"),
            onClick = {}
        )
    }
}
