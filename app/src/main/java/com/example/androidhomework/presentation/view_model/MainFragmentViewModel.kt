package com.example.androidhomework.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction

class MainFragmentViewModel:ViewModel() {

    data class CurrentState(
        val userNameTextView : String = "User",
        val newNotesList : List<Note> = emptyList(),
        val signOutBtn : Boolean = false,
        val addNewNoteBtn : Boolean = false,
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState()
    }

    fun handleAction(action: MainFragmentAction) {
        when(action) {
            is MainFragmentAction.SetUserName ->  _liveData.value = _liveData.value?.copy(userNameTextView = action.username)
            is MainFragmentAction.SetNoteList ->  _liveData.value = _liveData.value?.copy(newNotesList = action.list)
            MainFragmentAction.ReturnToRegistration -> _liveData.value = _liveData.value?.copy(signOutBtn = true)
             MainFragmentAction.AddNewNote -> _liveData.value = _liveData.value?.copy(addNewNoteBtn = true)
        }
    }

}
