package com.arnob.myapplication

import android.os.Binder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        binding.button.setOnClickListener{
            val name = binding.editText.text.toString()
            binding.textView.text = "Hello $name!"
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