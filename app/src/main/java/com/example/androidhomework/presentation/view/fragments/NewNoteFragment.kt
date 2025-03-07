package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.androidhomework.R
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.data.storage.room.DatabaseProvider
import com.example.androidhomework.data.storage.room.MyDatabase
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.data.storage.room.UserDao
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.actions.NewNoteFragmentActions
import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.NewNoteFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment:Fragment() {

    private val viewModel: NewNoteFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_new_note, container, false)
        return currentView
    }

    // create editTexts and progressBar
    private var newNoteHeader: AppCompatEditText? = null
    private var newNoteText: AppCompatEditText? = null
    private var pb: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val noteDao = database.noteDao()

        val currentUser = userPrefs.getUser()

        //initialize ediTexts
        newNoteHeader = view.findViewById(R.id.ann_header_acet)
        newNoteText = view.findViewById(R.id.ann_message_acet)
        pb = view.findViewById(R.id.ann_progressBar)

        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)
        if (currentUser != null) {
            initClickListener(saveBtn, currentUser, noteDao)
        }
    }

    private fun initClickListener(saveBtn:AppCompatButton, currentUser: User, noteDao : NoteDao){
        saveBtn.setOnClickListener {

            // Получаем текст непосредственно перед сохранением
            val headerText = newNoteHeader?.text?.toString() ?: ""
            val messageText = newNoteText?.text?.toString() ?: ""

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)

            viewModel.handleAction(NewNoteFragmentActions.SaveNote(headerText, messageText, noteDao, dateText, currentUser))

            observeViewModel()
        }
    }

    private fun observeViewModel(){
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state.let {
                if (it.saveAndReturnBtn) {
                    addNewNote()
                }
                if (it.justReturnBtn) {
                    returnToMainScreen()
                }
            }
        }
    }

    private fun switchProgressBarMode(flag: Boolean){
        if (flag){
            pb?.visibility = View.VISIBLE
        }
        else{
            pb?.visibility = View.INVISIBLE
        }

    }
    private fun addNewNote(){
        lifecycleScope.launch {
            switchProgressBarMode(true)
            delay(2000)
            switchProgressBarMode(false)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, MainFragment(), "MainFragment")
                .commit()
        }
    }

    private fun returnToMainScreen(){
        Toast.makeText(requireContext(), "Нет содержимого для сохранения. Заметка удалена.", Toast.LENGTH_SHORT).show()

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MainFragment(), "MainFragment")
            .commit()
    }

}