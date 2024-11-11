package com.example.androidhomework.ht21

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.ht18.RegistrationActivity
import com.example.androidhomework.ht19.Adapter
import com.example.androidhomework.ht19.NewNoteActivity
import com.example.androidhomework.ht19.Note

class MainActivity : AppCompatActivity() {

//    private val listOfNotes: ArrayList<Note> = ArrayList()
//    private lateinit var adapter: Adapter
//
//    companion object {
//        const val REQUEST_CODE_NEW_NOTE = 1
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.splashFragmentContainerView, SplashFragment(), "SplashFragment")
                .commit()
        }

//        val username = intent.getStringExtra("username")
//        val userNameTextView = findViewById<AppCompatTextView>(R.id.am_userName_actv)
//        userNameTextView.text = username
//
//        val recyclerView = findViewById<RecyclerView>(R.id.am_notes_rv)
//        adapter = Adapter(listOfNotes){view, position: Int ->
//            showPopupMenu(view, position)
//        }
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//        val addNewNoteBtn = findViewById<AppCompatButton>(R.id.am_addNewNote_acb)
//        addNewNoteBtn.setOnClickListener {
//            val intent = Intent(this, NewNoteActivity::class.java)
//            intent.putParcelableArrayListExtra("notesList", listOfNotes)
//            startActivityForResult(intent, REQUEST_CODE_NEW_NOTE)
//        }
//
//        val signOutBtn = findViewById<AppCompatButton>(R.id.am_signOut_btn)
//        signOutBtn.setOnClickListener {
//            val intent = Intent(this, RegistrationActivity::class.java)
//            startActivity(intent)
//        }
    }

//    fun showPopupMenu(view: View, position: Int){
//        val popupMenu = PopupMenu(view.context, view)
//        popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener { menuItem : MenuItem ->
//            when(menuItem.itemId){
//                R.id.menu_delete -> {
//                    if (position >= 0 && position < listOfNotes.size) {
//                        listOfNotes.removeAt(position)
//                        adapter.notifyItemRemoved(position)
//
//                    }
//                    else if (listOfNotes.isEmpty()) {
//                        adapter.notifyDataSetChanged()
//                    }
//                    true
//                }
//                else -> false
//            }
//        }
//        popupMenu.show()
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_NEW_NOTE && resultCode == Activity.RESULT_OK) {
//            val updatedNotesList = data?.getParcelableArrayListExtra<Note>("updatedNotesList")
//            if (updatedNotesList != null) {
//                listOfNotes.clear()
//                listOfNotes.addAll(updatedNotesList)
//                adapter.notifyDataSetChanged()
//            }
//        }
//    }
}