package com.example.androidhomework.ht19

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomework.R

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_note)

        var newNoteHeader : AppCompatEditText? = null
        newNoteHeader = findViewById(R.id.ann_header_acet)
        val headerText = newNoteHeader?.text?.toString() ?: ""

        var newNoteText : AppCompatEditText? = null
        newNoteText = findViewById(R.id.ann_message_acet)
        val messageText = newNoteText?.text?.toString() ?: ""

        val saveBtn = findViewById<AppCompatButton>(R.id.ann_save_acb)

        saveBtn.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("headerNote", headerText)
                putExtra("messageNote", messageText)
            }
            setResult(Activity.RESULT_OK, resultIntent) // Устанавливаем результат
            finish() // Закрываем NewNoteActivity

        }


    }
}