package com.arnob.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private var contacts : List<Contact>,
    private val onContactClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textPhone: TextView = itemView.findViewById(R.id.textPhone)
        val textEmail: TextView = itemView.findViewById(R.id.textEmail)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contact = contacts[position]

        holder.textName.text = contact.name
        holder.textPhone.text = contact.phone
        holder.textEmail.text = contact.email

        // Set click listener
        holder.itemView.setOnClickListener {
            onContactClick(contact)
        }
    }
    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged() // For simplicity; consider using DiffUtil for better performance
    }
    override fun getItemCount() = contacts.size
}
