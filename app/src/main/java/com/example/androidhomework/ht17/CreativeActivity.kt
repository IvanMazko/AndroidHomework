package com.example.androidhomework.ht17

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomework.R

class CreativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_creative)
        val button = findViewById<Button>(R.id.Translate_button)
        val text = findViewById<TextView>(R.id.tvTextToTranslate)
        button.setOnClickListener {
            text.text = resources.getString(R.string.myText)
        }
    }
}