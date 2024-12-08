package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidhomework.R
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.NewNoteFragmentViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment:Fragment() {

    private var viewModel: NewNoteFragmentViewModel ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(NewNoteFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_new_note, container, false)
        return currentView
    }

//    companion object{
//        fun newInstance(notesList: ArrayList<Note>): MainFragment {
//            val mainFragment = MainFragment()
//            val args = Bundle()
//            args.putParcelableArrayList("updatedNotesList", notesList)
//            mainFragment.arguments = args
//            return mainFragment
//        }
//    }
    private var newNoteHeader: AppCompatEditText? = null
    private var newNoteText: AppCompatEditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем переданный список заметок
        val notesList: ArrayList<Note>? = arguments?.getParcelableArrayList("notesList")

        newNoteHeader = view.findViewById(R.id.ann_header_acet)
        val headerText = newNoteHeader?.text?.toString() ?: ""
        newNoteText = view.findViewById(R.id.ann_message_acet)
        val messageText = newNoteText?.text?.toString() ?: ""

        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)

        initClickListener(saveBtn, notesList, headerText, messageText)
    }

    private fun initClickListener(saveBtn:AppCompatButton, notesList:ArrayList<Note>?, headerText:String, messageText:String){
        saveBtn.setOnClickListener {

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)
            // Добавляем новую заметку в список
            notesList?.add(Note(headerText, messageText, dateText))

            // Возвращаем обновлённый список обратно в MainActivity
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, viewModel!!.toNextScreen(notesList), "MainFragment")
                .commit()
        }
    }

}