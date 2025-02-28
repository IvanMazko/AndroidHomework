package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

class MainFragmentViewModel:ViewModel() {

    data class CurrentState(
        val userNameTextView : String= "User",
        //val newNotesList : List<Note> = List,
        val signOutBtn : Boolean = false,
        val addNewNoteBtn : Boolean = false,
        val transmittableNotesList : Bundle = Bundle()
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        Log.d("AAA", "VM Init")
        _liveData.value = CurrentState()
    }

    fun handleAction(action: MainFragmentAction) {
        when(action) {
            is MainFragmentAction.SetUserName -> {
                Log.d("MainFragmentViewModel", "SetUserName: $action.username")
                _liveData.value = _liveData.value?.copy(userNameTextView = action.username)
            }
            is MainFragmentAction.SetNoteList -> {
                Log.d("MainFragmentViewModel", "SetNoteList: $action.list")
               // _liveData.value = _liveData.value?.copy(newNotesList = action.list)
            }
            MainFragmentAction.ReturnToRegistration -> {
                Log.d("MainFragmentViewModel", "ReturnToRegistration")
                _liveData.value = _liveData.value?.copy(signOutBtn = true)
            }
             MainFragmentAction.AddNewNote -> {
                Log.d("MainFragmentViewModel", "AddNewNote")
                toNextScreen()
            }
        }
    }

    private fun toNextScreen(){
        val args = Bundle()
        //args.putParcelableArrayList("notesList", _liveData.value?.newNotesList) // Передаётся старый список, а не тот, который получился после удаления заметок
        args.putString("username", _liveData.value?.userNameTextView)
        _liveData.value = _liveData.value?.copy(transmittableNotesList = args, addNewNoteBtn = true)
    }


}
//    private fun updateNoteList(){
//        _liveData.value = CurrentState()
//    }
//MainFragmentAction.UpdateNoteList -> updateNoteList()