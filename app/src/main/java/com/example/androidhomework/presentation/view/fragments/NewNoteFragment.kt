package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var notesList: ArrayList<Note> ?= null

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

    // create editTexts
    private var newNoteHeader: AppCompatEditText? = null
    private var newNoteText: AppCompatEditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем переданный список заметок
         notesList = arguments?.getParcelableArrayList("notesList")

        //initialize ediTexts
        newNoteHeader = view.findViewById(R.id.ann_header_acet)
        newNoteText = view.findViewById(R.id.ann_message_acet)


        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)
        initClickListener(saveBtn, notesList)
    }

    private fun initClickListener(saveBtn:AppCompatButton, notesList:ArrayList<Note>?){
        saveBtn.setOnClickListener {
            // Получаем текст непосредственно перед сохранением
            val headerText = newNoteHeader?.text?.toString() ?: ""
            val messageText = newNoteText?.text?.toString() ?: ""
            viewModel?.checkData(headerText, messageText, notesList)

            observeViewModel()
        }
    }

    private fun observeViewModel(){
        viewModel?.publicLiveData?.observe(viewLifecycleOwner){ newData ->
            if (newData != null){
                toMainScreen(newData)
            }
            else{
                Toast.makeText(requireContext(), "Нет содержимого для сохранения. Заметка удалена.", Toast.LENGTH_SHORT).show() // мне нужно, чтобы происходил переход на главный экран и одновременно высвечивалась эта надпись, поэтому ниже пришлось объявлять args, использовать  parentFragmentManager и тд
                val mainFragment = MainFragment()
                val args = Bundle()
                args.putParcelableArrayList("updatedNotesList", notesList ?: ArrayList()) // должен передаваться список, который пришёл (без изменений)
                mainFragment.arguments = args
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, mainFragment, "MainFragment")
                    .commit()
            }

        }
    }

    // Возвращаем обновлённый список обратно в MainActivity
    private fun toMainScreen(newData:Bundle){
        val mainFragment = MainFragment()
        mainFragment.arguments = newData
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, mainFragment, "MainFragment")
            .commit()
    }

}