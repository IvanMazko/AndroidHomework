package com.example.androidhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val intent1 = Intent(this@MainActivity,FirstNoteActivity::class.java)
        val intent2 = Intent(this@MainActivity,SecondNoteActivity::class.java)
        val intent3 = Intent(this@MainActivity,ThirdNodeActivity::class.java)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button1.setOnClickListener{
            startActivity(intent1)
        }
        button2.setOnClickListener{
            startActivity(intent2)
        }
        button3.setOnClickListener{
            startActivity(intent3)
        }
    }

}