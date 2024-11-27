package com.example.androidhomework.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationFragmentViewModel: ViewModel() {
    private val liveData: MutableLiveData<String> = MutableLiveData(null)
    val publicLiveData: LiveData<String> = liveData
}