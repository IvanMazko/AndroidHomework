package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

class MainFragmentViewModel:ViewModel() {

    data class CurrentState(
        val userNameTextView : String = "User",
        val notesList : ArrayList<Note?> = ArrayList(),
        val signOutBtn : Boolean = false,
        val addNewNoteBtn : Boolean= false,
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    fun handleAction(action: MainFragmentAction, username: String, listOfNotes: ArrayList<Note?>){
        when(action){
            MainFragmentAction.SetUserName -> _liveData.value = CurrentState(userNameTextView = username)
            MainFragmentAction.SetNoteList ->_liveData.value = CurrentState(notesList = listOfNotes)
            MainFragmentAction.ReturnToRegistration -> _liveData.value = CurrentState(signOutBtn = true)
            MainFragmentAction.AddNewNote -> _liveData.value = CurrentState(addNewNoteBtn = true)
        }
    }


    fun toNextScreen(listOfNotes: ArrayList<Note>): NewNoteFragment {
        val newNoteFragment = NewNoteFragment()
        val args = Bundle()
        args.putParcelableArrayList("notesList", listOfNotes)
        newNoteFragment.arguments = args
        return newNoteFragment
    }
}