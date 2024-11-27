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
import com.example.androidhomework.presentation.view.Adapter
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.view_model.ViewModel

class MainFragment : Fragment() {

    private val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)  .create(ViewModel::class.java)
    private val listOfNotes: ArrayList<Note> = ArrayList()
    private var adapter: Adapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_main, container, false)
        return currentView
    }



    companion object {
        fun newInstance(listOfNotes: ArrayList<Note>): NewNoteFragment {
            val newNoteFragment = NewNoteFragment()
            val args = Bundle()
            args.putParcelableArrayList("notesList", listOfNotes)
            newNoteFragment.arguments = args
            return newNoteFragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("username")
        val userNameTextView = view.findViewById<AppCompatTextView>(R.id.am_userName_actv)
        userNameTextView.text = username

        val recyclerView = view.findViewById<RecyclerView>(R.id.am_notes_rv)
        adapter = Adapter(listOfNotes){view, position: Int ->
            showPopupMenu(view, position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val addNewNoteBtn = view.findViewById<AppCompatButton>(R.id.am_addNewNote_acb)
        addNewNoteBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, newInstance(listOfNotes),"NewNoteFragment")
                .commit()
        }

        val signOutBtn = view.findViewById<AppCompatButton>(R.id.am_signOut_btn)
        signOutBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RegistrationFragment(),"RegistrationFragment")
                .commit()
        }


        val updatedNotesList: ArrayList<Note>? = arguments?.getParcelableArrayList("updatedNotesList")
        if (updatedNotesList != null) {
            listOfNotes.clear()
            listOfNotes.addAll(updatedNotesList)
            adapter?.notifyDataSetChanged()
        }
    }

    fun showPopupMenu(view: View, position: Int){
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem : MenuItem ->
            when(menuItem.itemId){
                R.id.menu_delete -> {
                    if (position >= 0 && position < listOfNotes.size) {
                        listOfNotes.removeAt(position)
                        adapter?.notifyItemRemoved(position)

                    }
                    else if (listOfNotes.isEmpty()) {
                        adapter?.notifyDataSetChanged()
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

}

