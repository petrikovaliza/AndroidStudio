package com.example.secondproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listview)
        val user_edit_text :EditText = findViewById(R.id.user_data)
        val button: Button = findViewById(R.id.my_button)

        val todos: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, todos)
        listView.adapter = adapter

        listView.setOnItemClickListener() { adapterView, view, i, l ->
            val text = listView.getItemAtPosition(i).toString()
            adapter.remove(text)

            Toast.makeText(this, "Удаленно: $text", Toast.LENGTH_LONG).show()
        }
        button.setOnClickListener()
        {
            val text = user_edit_text.text.toString().trim()
            if(text != "")
                adapter.insert(text, 0)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}