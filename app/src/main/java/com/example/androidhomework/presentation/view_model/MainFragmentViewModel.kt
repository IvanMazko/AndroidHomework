package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.view.fragments.NewNoteFragment

class MainFragmentViewModel:ViewModel() {

    private val _userNameLiveData: MutableLiveData<String> = MutableLiveData(null)
    val userNameLiveData: LiveData<String> = _userNameLiveData

    private val _noteListLiveData: MutableLiveData<ArrayList<Note>?> = MutableLiveData()
    val noteListLiveData:LiveData<ArrayList<Note>?> =_noteListLiveData


    fun handleAction(action: MainFragmentAction, userName: String, listOfNotes: ArrayList<Note>){
        when(action){
            MainFragmentAction.SetUserName -> setUserName(userName)
            MainFragmentAction.SetListOfNotes -> setNoteList(listOfNotes)
        }
    }

    fun setUserName(userName:String){
        _userNameLiveData.value = userName
    }

    fun setNoteList(noteList: ArrayList<Note>?){
        _noteListLiveData.value = noteList
    }

    fun toNextScreen(listOfNotes: ArrayList<Note>): NewNoteFragment {
        val newNoteFragment = NewNoteFragment()
        val args = Bundle()
        args.putParcelableArrayList("notesList", listOfNotes)
        newNoteFragment.arguments = args
        return newNoteFragment
    }
}