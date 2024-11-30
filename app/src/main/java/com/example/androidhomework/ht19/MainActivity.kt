package com.example.androidhomework.ht19

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.R.id.ann_save_acb
import com.example.androidhomework.ht18.RegistrationActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val listOfNotes: ArrayList<Custom> = ArrayList()
    private lateinit var adapter: Adapter

    companion object {
        const val REQUEST_CODE_NEW_NOTE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")
        val userNameTextView = findViewById<AppCompatTextView>(R.id.am_userName_actv)
        userNameTextView.text = username

        val recyclerView = findViewById<RecyclerView>(R.id.am_notes_rv)
        adapter = Adapter(listOfNotes){view, position: Int ->
            showPopupMenu(view, position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val addNewNoteBtn = findViewById<AppCompatButton>(R.id.am_newNote_acb)
        addNewNoteBtn.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            intent.putParcelableArrayListExtra("notesList", listOfNotes)
            startActivityForResult(intent, REQUEST_CODE_NEW_NOTE)
        }

        val signOutBtn = findViewById<AppCompatButton>(R.id.am_signOut_btn)
        signOutBtn.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    fun showPopupMenu(view: View, position: Int){
        val note = listOfNotes[position];
        val popupMenu = PopupMenu(view.context, view)

        when(note){
            is Custom.Note -> {
                popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
            }
            is Custom.Icon -> {
                popupMenu.menuInflater.inflate(R.menu.icon_options_menu, popupMenu.menu)
            }
        }

        popupMenu.setOnMenuItemClickListener { menuItem : MenuItem ->
            when(menuItem.itemId){
                R.id.menu_delete -> {
                    deleteNote(listOfNotes, position)
                    true
                }
                R.id.menu_share -> {
                    if(note is Custom.Note){
                        shareNote(note)
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    fun deleteNote(listOfNotes : ArrayList<Custom>, position: Int){
        listOfNotes.removeAt(position)
        adapter.notifyDataSetChanged()
    }
    fun shareNote(note: Custom.Note){
        val message = "Header: ${note.header}\nMessage: ${note.message}\nDate: ${note.date}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        val chosenIntent = Intent.createChooser(intent, "Share with:")
        startActivity(chosenIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_NEW_NOTE && resultCode == Activity.RESULT_OK) {
            val updatedNotesList = data?.getParcelableArrayListExtra<Custom.Note>("updatedNotesList")
            if (updatedNotesList != null) {
                listOfNotes.clear()
                listOfNotes.addAll(updatedNotesList)
                adapter.notifyDataSetChanged()
            }
        }
    }
}