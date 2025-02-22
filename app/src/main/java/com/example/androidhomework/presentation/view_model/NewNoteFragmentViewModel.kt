package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.NewNoteFragmentActions

class NewNoteFragmentViewModel: ViewModel() {

    data class CurrentEstate(
        val progressBarFlag : Boolean = false
    )
    private val _liveData = MutableLiveData<CurrentEstate>()
    val liveData: LiveData<CurrentEstate> get() = _liveData


    fun handleAction(actions: NewNoteFragmentActions){
        when(actions){
            is NewNoteFragmentActions.ChangeProgressBarStatus -> {
                _liveData.value = CurrentEstate(progressBarFlag = actions.flag)
            }
        }
    }

    fun checkData(notesList: ArrayList<Note>?, headerText : String, messageText : String, dateText : String) : ArrayList<Note>{
        val updatedList = notesList ?: ArrayList()
        if (headerText.isNotEmpty() || messageText.isNotEmpty()) {
            // Добавляем новую заметку в список
            updatedList.add(Note(headerText, messageText, dateText))
        }
        return updatedList
    }
}