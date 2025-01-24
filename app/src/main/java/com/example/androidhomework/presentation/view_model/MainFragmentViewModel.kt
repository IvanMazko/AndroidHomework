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
        val newNotesList : ArrayList<Note>? = ArrayList(),
        val signOutBtn : Boolean = false,
        val addNewNoteBtn : Boolean = false,
        val transmittableNotesList : Bundle = Bundle()
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    fun handleAction(action: MainFragmentAction, username: String, listOfNotes: ArrayList<Note>?){
        when(action){
            MainFragmentAction.SetUserName -> _liveData.value = CurrentState(userNameTextView = username)
            MainFragmentAction.SetNoteList ->_liveData.value?.copy(newNotesList = listOfNotes)
            MainFragmentAction.ReturnToRegistration -> _liveData.value = CurrentState(signOutBtn = true)
            MainFragmentAction.AddNewNote -> toNextScreen(listOfNotes)
        }
    }


    fun toNextScreen(listOfNotes: ArrayList<Note>?){
        val args = Bundle()
        args.putParcelableArrayList("notesList", listOfNotes)
        _liveData.value?.copy(transmittableNotesList = args)
        _liveData.value = CurrentState(addNewNoteBtn = true)
    }
}