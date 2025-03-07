package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.data.storage.room.UserDao
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.NewNoteFragmentActions
import com.example.androidhomework.presentation.view.fragments.MainFragment
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewNoteFragmentViewModel: ViewModel() {

    data class CurrentEstate(
        val saveAndReturnBtn : Boolean = false,
        val justReturnBtn : Boolean = false
    )
    private val _liveData = MutableLiveData<CurrentEstate>()
    val liveData: LiveData<CurrentEstate> get() = _liveData

    init {
        _liveData.value = CurrentEstate()
    }

    fun handleAction(actions: NewNoteFragmentActions){
        when(actions){
            is NewNoteFragmentActions.SaveNote -> checkData(actions.header, actions.message, actions.noteDao, actions.dateText, actions.currentUser)
        }
    }


    private fun checkData(headerText : String, messageText : String, noteDao: NoteDao, dateText : String, currentUser: User){
        if (headerText.isNotEmpty() || messageText.isNotEmpty()) {
            _liveData.value = _liveData.value?.copy(saveAndReturnBtn = true)
            viewModelScope.launch(Dispatchers.IO) {
                noteDao.putNote(Note(0, headerText, messageText, dateText, currentUser.id))
            }
        }
        else {
            _liveData.value = _liveData.value?.copy(justReturnBtn = true)
        }
    }


}