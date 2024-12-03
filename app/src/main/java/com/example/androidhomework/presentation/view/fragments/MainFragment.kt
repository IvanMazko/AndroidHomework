package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.databinding.ActivityMainBinding
import com.example.androidhomework.presentation.view.Adapter
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel
import com.example.androidhomework.presentation.view_model.ViewModel

class MainFragment : Fragment() {

    private var viewModel: MainFragmentViewModel ?= null
    private val listOfNotes: ArrayList<Note> = ArrayList()
    private var adapter: Adapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(MainFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_main, container, false)
        return currentView
    }



//    companion object {
//        fun newInstance(listOfNotes: ArrayList<Note>): NewNoteFragment {
//            val newNoteFragment = NewNoteFragment()
//            val args = Bundle()
//            args.putParcelableArrayList("notesList", listOfNotes)
//            newNoteFragment.arguments = args
//            return newNoteFragment
//        }
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        _binding = ActivityMainBinding.inflate(this.layoutInflater)
//        viewModel.liveData.observe(this) { newData ->
//            binding.
//        }

        val username = arguments?.getString("username") ?: "Default userName"
        viewModel?.setUserName(username)
        val userNameTextView = view.findViewById<AppCompatTextView>(R.id.am_userName_actv)

        viewModel?.userNameLiveData?.observe(this.viewLifecycleOwner){ newData ->
            updateUserName(userNameTextView, newData)
        }


        val recyclerView = view.findViewById<RecyclerView>(R.id.am_notes_rv)
        adapter = Adapter(listOfNotes){view, position: Int ->
            showPopupMenu(view, position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val addNewNoteBtn = view.findViewById<AppCompatButton>(R.id.am_addNewNote_acb)
        val signOutBtn = view.findViewById<AppCompatButton>(R.id.am_signOut_btn)
        initClickListeners(addNewNoteBtn, signOutBtn)


        val updatedNotesList: ArrayList<Note>? = arguments?.getParcelableArrayList("updatedNotesList")
        viewModel?.setNoteList(updatedNotesList)

        viewModel?.noteListLiveData?.observe(this.viewLifecycleOwner){newData ->
            updateNoteList(newData)
        }


//        if (updatedNotesList != null) {
//            listOfNotes.clear()
//            listOfNotes.addAll(updatedNotesList)
//            adapter?.notifyDataSetChanged()
//        }
    }

    private fun updateUserName(userNameTextView: AppCompatTextView, username: String){
        userNameTextView.text = username
    }

    private fun updateNoteList(noteList:ArrayList<Note>?){
        if (noteList != null) {
            listOfNotes.clear()
            listOfNotes.addAll(noteList)
            adapter?.notifyDataSetChanged()
        }
    }
    fun initClickListeners(addNewNoteBtn:AppCompatButton, signOutBtn:AppCompatButton){
        addNewNoteBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, viewModel!!.toNextScreen(listOfNotes),"NewNoteFragment")
                .commit()
        }
        signOutBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RegistrationFragment(),"RegistrationFragment")
                .commit()
        }
    }

    fun showPopupMenu(view: View, position: Int){
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem : MenuItem ->
            when(menuItem.itemId){
                R.id.menu_delete -> {
                    listOfNotes.removeAt(position)
                    adapter?.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

}

