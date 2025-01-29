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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment:Fragment() {

    private val viewModel: MainFragmentViewModel? by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(NewNoteFragmentViewModel::class.java)
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

        val username = arguments?.getString("username") ?: "Default userName"

        // Получаем переданный список заметок
        val notesList: ArrayList<Note>? = arguments?.getParcelableArrayList("notesList")

        //initialize ediTexts
        newNoteHeader = view.findViewById(R.id.ann_header_acet)
        newNoteText = view.findViewById(R.id.ann_message_acet)


        val saveBtn = view.findViewById<AppCompatButton>(R.id.ann_save_acb)
        initClickListener(saveBtn, notesList, username)
    }

    private fun initClickListener(saveBtn:AppCompatButton, notesList:ArrayList<Note>?, username: String){
        saveBtn.setOnClickListener {

            // Получаем текст непосредственно перед сохранением
            val headerText = newNoteHeader?.text?.toString() ?: ""
            val messageText = newNoteText?.text?.toString() ?: ""

            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)


            val updatedNoteList = viewModel?.checkData(notesList, headerText, messageText, dateText)



            if (headerText.isEmpty() && messageText.isEmpty()) {
                Toast.makeText(requireContext(), "Нет содержимого для сохранения. Заметка удалена.", Toast.LENGTH_SHORT).show()
            }

            val mainFragment = MainFragment()
            val args = Bundle()
            args.putString("username", username)
            args.putParcelableArrayList("updatedNotesList", updatedNoteList ?: ArrayList())
            mainFragment.arguments = args

            // Возвращаем обновлённый список обратно в MainActivity
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, mainFragment, "MainFragment")
                .commit()
        }
    }

}
//            val checkingList = Bundle()
//            checkingList.putParcelableArrayList("check", notesList)