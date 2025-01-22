package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view.fragments.MainFragment
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragmentViewModel: ViewModel() {
    private val liveData: MutableLiveData<Bundle?> = MutableLiveData(null)
    val publicLiveData: LiveData<Bundle?> = liveData

    fun checkData(headerText: String, messageText: String, notesList: ArrayList<Note>?) {
        if (headerText.isEmpty() && messageText.isEmpty()){
            liveData.value = null
        }
        else{
            val args = Bundle()

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)
            notesList?.add(Note(headerText, messageText, dateText))

            args.putParcelableArrayList("updatedNotesList", notesList)
            liveData.value = args
        }

    }
}