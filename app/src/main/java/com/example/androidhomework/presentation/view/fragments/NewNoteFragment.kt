package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhomework.R
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.data.storage.room.DatabaseProvider
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.databinding.ActivityNewNoteBinding
import com.example.androidhomework.presentation.actions.NewNoteFragmentActions
import com.example.androidhomework.presentation.view_model.NewNoteFragmentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment:Fragment() {

    private val viewModel: NewNoteFragmentViewModel by viewModels()

    private var _binding : ActivityNewNoteBinding ?= null
    private val binding : ActivityNewNoteBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityNewNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val noteDao = database.noteDao()

        val currentUser = userPrefs.getUser()

        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)
        if (currentUser != null) {
            initClickListener(saveBtn, currentUser, noteDao)
        }
    }

    private fun initClickListener(saveBtn:AppCompatButton, currentUser: User, noteDao : NoteDao){
        saveBtn.setOnClickListener {

            // Получаем текст непосредственно перед сохранением
            val headerText = _binding?.annHeaderAcet?.text?.toString() ?: ""
            val messageText = _binding?.annMessageAcet?.text?.toString() ?: ""

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
            _binding?.annProgressBar?.visibility = View.VISIBLE
        }
        else{
            _binding?.annProgressBar?.visibility = View.INVISIBLE
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}