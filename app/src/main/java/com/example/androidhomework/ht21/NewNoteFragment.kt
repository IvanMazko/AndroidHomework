package com.example.androidhomework.ht21

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import com.example.androidhomework.ht19.Note
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_new_note, container, false)
        return currentView
    }

    companion object{
        fun newInstance(notesList: ArrayList<Note>): MainFragment{
            val mainFragment = MainFragment()
            val args = Bundle()
            args.putParcelableArrayList("updatedNotesList", notesList)
            mainFragment.arguments = args
            return mainFragment
        }
    }
    private var newNoteHeader: AppCompatEditText? = null
    private var newNoteText: AppCompatEditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем переданный список заметок
        val notesList: ArrayList<Note>? = arguments?.getParcelableArrayList("notesList")

        newNoteHeader = view.findViewById(R.id.ann_header_acet)
        newNoteText = view.findViewById(R.id.ann_message_acet)

        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)

        saveBtn.setOnClickListener {
            val headerText = newNoteHeader?.text?.toString() ?: ""
            val messageText = newNoteText?.text?.toString() ?: ""

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)
            // Добавляем новую заметку в список
            notesList?.add(Note(headerText, messageText, dateText))

            // Возвращаем обновлённый список обратно в MainActivity
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, newInstance(notesList!!), "MainFragment")
                .commit()
        }
    }

}