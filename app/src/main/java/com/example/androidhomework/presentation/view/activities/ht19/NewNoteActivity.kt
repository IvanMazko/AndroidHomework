package com.example.androidhomework.presentation.view.activities.ht19

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.androidhomework.R
import com.example.androidhomework.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteActivity : AppCompatActivity() {

    private var newNoteHeader: AppCompatEditText? = null
    private var newNoteText: AppCompatEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_note)

        // Получаем переданный список заметок
        val notesList = intent.getParcelableArrayListExtra<Note>("notesList") ?: ArrayList()

        newNoteHeader = findViewById(R.id.ann_header_acet)
        newNoteText = findViewById(R.id.ann_message_acet)


        val saveBtn = findViewById<AppCompatButton>(R.id.ann_save_acb)

        saveBtn.setOnClickListener {
            val id = 1
            val headerText = newNoteHeader?.text?.toString() ?: ""
            val messageText = newNoteText?.text?.toString() ?: ""

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)
            // Добавляем новую заметку в список
            notesList.add(Note(id, headerText, messageText, dateText))

            // Возвращаем обновлённый список обратно в MainActivity
            val resultIntent = Intent()
            resultIntent.putParcelableArrayListExtra("updatedNotesList", notesList)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }



    }
}