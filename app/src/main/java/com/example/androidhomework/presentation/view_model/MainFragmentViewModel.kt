package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentActions

class MainFragmentViewModel:ViewModel() {

    data class CurrentState(
        val userNameTextView : String= "User",
        val newNotesList : ArrayList<Note>? = ArrayList(),
        val signOutBtn : Boolean = false,
        val addNewNoteBtn : Boolean = false,
        val transmittableNotesList : Bundle = Bundle(),
        val getAllCharactersBtn : Boolean = false,
        val getOneCharacterByIdBtn : Boolean = false,
        val getOneCharacterByNameBtn : Boolean = false
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        Log.d("AAA", "VM Init")
        _liveData.value = CurrentState()
    }

    fun handleAction(action: MainFragmentActions) {
        when(action) {
            is MainFragmentActions.SetUserName -> {
                Log.d("MainFragmentViewModel", "SetUserName: $action.username")
                _liveData.value = _liveData.value?.copy(userNameTextView = action.username)
            }
            is MainFragmentActions.SetNoteList -> {
                Log.d("MainFragmentViewModel", "SetNoteList: $action.list")
                _liveData.value = _liveData.value?.copy(newNotesList = action.list)
            }
            MainFragmentActions.ReturnToRegistration -> {
                Log.d("MainFragmentViewModel", "ReturnToRegistration")
                _liveData.value = _liveData.value?.copy(signOutBtn = true)
            }
             MainFragmentActions.AddNewNote -> {
                Log.d("MainFragmentViewModel", "AddNewNote")
                toNextScreen()
            }
            MainFragmentActions.ShowAllCharactersInLogs -> {
                _liveData.value = _liveData.value?.copy(getAllCharactersBtn = true, getOneCharacterByIdBtn = false, getOneCharacterByNameBtn = false)
            }
            MainFragmentActions.ShowCurrentCharacterByIdInLogs -> {
                _liveData.value = _liveData.value?.copy(getOneCharacterByIdBtn = true, getAllCharactersBtn = false, getOneCharacterByNameBtn = false)
            }
            MainFragmentActions.ShowCurrentCharacterByNameInLogs -> {
                _liveData.value = _liveData.value?.copy(getOneCharacterByNameBtn = true, getAllCharactersBtn = false, getOneCharacterByIdBtn = false)
            }
        }
    }

    private fun toNextScreen(){
        val args = Bundle()
        args.putParcelableArrayList("notesList", _liveData.value?.newNotesList)
        args.putString("username", _liveData.value?.userNameTextView)
        _liveData.value = _liveData.value?.copy(transmittableNotesList = args, addNewNoteBtn = true)
    }


}
