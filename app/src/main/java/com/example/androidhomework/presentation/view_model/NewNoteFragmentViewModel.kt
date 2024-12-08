package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view.fragments.MainFragment
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

class NewNoteFragmentViewModel: ViewModel() {

    fun toNextScreen(notesList: ArrayList<Note>?): MainFragment {
        val mainFragment = MainFragment()
        val args = Bundle()
        args.putParcelableArrayList("updatedNotesList", notesList)
        mainFragment.arguments = args
        return mainFragment
    }
}