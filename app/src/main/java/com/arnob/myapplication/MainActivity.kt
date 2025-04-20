package com.arnob.myapplication

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arnob.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textView
        val editText = binding.editText
        val button = binding.button

        val emailInput = binding.materialTextInput.editText?.text
        val materialButton = binding.materialButton

        binding.materialButton.setOnClickListener {
            val email = binding.materialTextInput.editText?.text.toString()
            // Do something with the email
            Toast.makeText(this, "Email: $email", Toast.LENGTH_SHORT).show()
        }

        binding.button.setOnClickListener{
            val name = binding.editText.text.toString()
            val intent  = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("NAME_KEY", name) // Optional: Pass data to the second activity
            startActivity(intent)
            /*
            First activity's onPause() is called
            Second activity's onCreate(), onStart(), and onResume() are called
            First activity's onStop() is called
            When returning, second activity's onPause() is called
            First activity's onRestart(), onStart(), and onResume() are called
            Second activity's onStop() and onDestroy() are called
            */

        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Called before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Called during text changes
            }

            override fun afterTextChanged(s: Editable?) {
                // Called after text changes
                binding.textView.text = "Typing: ${s.toString()}"
            }
        })

    }
}