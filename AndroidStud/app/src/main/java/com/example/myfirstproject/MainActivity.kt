package com.example.myfirstproject

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val label = findViewById<TextView>(R.id.main_label)
        val user_edit_text: EditText = findViewById(R.id.user_data)
        val button: Button = findViewById(R.id.my_button)
        val image: ImageView = findViewById(R.id.my_picture)

        button.setOnClickListener()
        {
            val text = user_edit_text.text.toString().trim()
            if(text == "hello world")
            {
                Toast.makeText(this, "Мой первый андроид код", Toast.LENGTH_SHORT).show()
                image.setImageResource(R.drawable.bot)
                image.visibility = View.VISIBLE

            }
            else
            {
                label.text = text
                image.visibility = View.GONE

            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}