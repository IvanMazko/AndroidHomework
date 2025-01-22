package com.example.androidhomework.presentation.view.activities.ht18

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.androidhomework.R
import com.example.androidhomework.presentation.view.activities.MainActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        var login: AppCompatEditText? = null
        var password: AppCompatEditText? = null

        login = findViewById(R.id.login_et)
        password = findViewById(R.id.password_et)

        val button = findViewById<Button>(R.id.sign_up_btn)

        button.setOnClickListener {
            if (login.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(this, "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java).also {
                    it.putExtra("username", login.text.toString())
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}