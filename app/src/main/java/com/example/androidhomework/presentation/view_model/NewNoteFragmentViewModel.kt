package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view.fragments.MainFragment
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

class NewNoteFragmentViewModel: ViewModel() {

    fun checkData(notesList: ArrayList<Note>?, headerText : String, messageText : String, dateText : String) : Bundle{
        if (headerText.isNotEmpty() || messageText.isNotEmpty()) {
            // Добавляем новую заметку в список
            notesList?.add(Note(headerText, messageText, dateText))
        }
        val args = Bundle()
        args.putParcelableArrayList("updatedNotesList", notesList)
        return args
    }
}