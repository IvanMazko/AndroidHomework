package com.example.androidhomework.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note

class ViewModel : ViewModel() {
    val liveData = MutableLiveData<Note>()

    fun updateListOfNotes(listOfNotes : Note){
        liveData.value = listOfNotes
    }
}