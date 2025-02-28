package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.NewNoteFragmentActions
import com.example.androidhomework.presentation.view.fragments.MainFragment
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

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

    fun checkData(notesList: List<Note>?, headerText : String, messageText : String, dateText : String, dao: NoteDao) : List<Note>?{
        val updatedList = notesList
        if (headerText.isNotEmpty() || messageText.isNotEmpty()) {
            // Добавляем новую заметку в список
            //updatedList.add(Note(1, headerText, messageText, dateText))

            dao.putNote(Note(1, headerText, messageText, dateText))
        }
        return updatedList
    }

}