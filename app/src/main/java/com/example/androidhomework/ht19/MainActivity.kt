package com.example.androidhomework.ht19

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.ht16.FirstNoteActivity
import com.example.androidhomework.ht16.SecondNoteActivity
import com.example.androidhomework.ht16.ThirdNodeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var newNoteLauncher: ActivityResultLauncher<Intent>
    private val listOfNotes = mutableListOf<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        newNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Получаем данные из Intent
                val headerNote = result.data?.getStringExtra("headerNote") ?: ""
                val messageNote = result.data?.getStringExtra("messageNote") ?: ""
                // Добавляем новую заметку в список
                listOfNotes.add(Note(headerNote, messageNote, "Time"))
            }
        }
                //val currentNoteHeader = intent.getStringExtra("headerNote") ?: "Text note"
                //val currentNoteMessage = intent.getStringExtra("messageNote") ?: "The note is empty :("


        val recyclerView = findViewById<RecyclerView>(R.id.am_notes_rv)
        val adapter = Adapter(listOfNotes)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val username = intent.getStringExtra("username")

        var userNameTextView:AppCompatTextView? = null
        userNameTextView = findViewById(R.id.am_userName_tv)
        userNameTextView.text = username


//        val intent = Intent(this, NewNoteActivity::class.java)
//        val addNewNoteBtn = findViewById<AppCompatButton>(R.id.am_addNewNote_acb)
//        addNewNoteBtn.setOnClickListener {
//            startActivity(intent)
//        }

            findViewById<Button>(R.id.am_addNewNote_acb).setOnClickListener {
                val intent = Intent(this, NewNoteActivity::class.java)
                newNoteLauncher.launch(intent)
            }



    }

}