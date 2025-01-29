package com.example.androidhomework.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.compose.runtime.internal.updateLiveLiteralValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.databinding.ActivityMainBinding
import com.example.androidhomework.presentation.view.Adapter
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel

class MainFragment : Fragment() {

    private var viewModel: MainFragmentViewModel? = null
    private val listOfNotes: ArrayList<Note> = ArrayList()
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_main, container, false)
        return currentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing liveData and using Observer
        val username = arguments?.getString("username") ?: "Default userName"  // нужно, чтобы эти 2 строки выполнялись только при переходе из фрагмента регистрации, а во всех других случаях значение бралось из вьюмодели
        viewModel?.handleAction(MainFragmentAction.SetUserName(username)) // здесь вообще listOfNotes не нужен, добавляю только чтобы соответствовать параметрам handleAction()
        Log.e("!!!", this.hashCode().toString())
        val userNameTextView = view.findViewById<AppCompatTextView>(R.id.am_userName_actv)


        //usage of Adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.am_notes_rv)
        adapter = Adapter(listOfNotes) { view, position: Int ->
            showPopupMenu(view, position)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //creating buttons
        val addNewNoteBtn = view.findViewById<AppCompatButton>(R.id.am_addNewNote_acb)
        val signOutBtn = view.findViewById<AppCompatButton>(R.id.am_signOut_btn)
        initClickListeners(addNewNoteBtn, signOutBtn, username)


        //initializing liveData and using Observer
        val updatedNotesList: ArrayList<Note>? = arguments?.getParcelableArrayList("updatedNotesList")
        viewModel?.handleAction(MainFragmentAction.SetNoteList(updatedNotesList))



        observeViewModel(userNameTextView)
    }

//    private fun observeViewModel(userNameTextView: AppCompatTextView) {
//        viewModel?.liveData?.observe(this.viewLifecycleOwner) {
//            when{
//                it.signOutBtn -> {
//                    toNextScreen(RegistrationFragment(),"RegistrationFragment")
//                }
//                it.addNewNoteBtn -> {
//                    val newNoteFragment = NewNoteFragment()
//                    newNoteFragment.arguments = it.transmittableNotesList
//                    toNextScreen(newNoteFragment, "NewNoteFragment")
//                }
//            }
//            updateUserName(userNameTextView, it.userNameTextView)
//            updateNoteList(it.newNotesList) // somehow the newNotesList is null, despite the fact, that in toNextScreen it is not null
//        }
//
//    }

    private fun observeViewModel(userNameTextView: AppCompatTextView) {
        viewModel?.liveData?.observe(viewLifecycleOwner) { state ->
            Log.d("MainFragment", "Observed state: $state")
            state?.let {
                if (it.signOutBtn) {
                    toNextScreen(RegistrationFragment(), "RegistrationFragment")
                }
                if (it.addNewNoteBtn) {
                    val newNoteFragment = NewNoteFragment()
                    newNoteFragment.arguments = it.transmittableNotesList
                    toNextScreen(newNoteFragment, "NewNoteFragment")
                }
                updateUserName(userNameTextView, it.userNameTextView)
                if (it.newNotesList != null) {
                    updateNoteList(it.newNotesList)
                }
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
    }

      private fun updateUserName(userNameTextView: AppCompatTextView, username: String) {
          userNameTextView.text = username
      }

    private fun updateNoteList(noteList: ArrayList<Note>?) {
        if (noteList != null) {
           // Log.d("MainFragment", "Received newNotesList: $noteList") // Добавьте это для проверки
            listOfNotes.clear()
            listOfNotes.addAll(noteList)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun initClickListeners(addNewNoteBtn: AppCompatButton, signOutBtn: AppCompatButton, username: String) {
        addNewNoteBtn.setOnClickListener {
            viewModel?.handleAction(MainFragmentAction.AddNewNote)
        }
        signOutBtn.setOnClickListener {
            viewModel?.handleAction(MainFragmentAction.ReturnToRegistration)
        }
    }




    private fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_delete -> {
                    deleteNote(listOfNotes, position)
                    true
                }

                R.id.menu_share -> {
                    shareNote(listOfNotes[position])
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun deleteNote(listOfNotes: ArrayList<Note>, position: Int) {
        listOfNotes.removeAt(position)
        Log.d("MainFragment", "Updated list after deletion: $listOfNotes")
        adapter?.notifyItemRemoved(position)  // Notify the adapter about item removal
        viewModel?.handleAction(MainFragmentAction.SetNoteList(listOfNotes))
    }

    private fun shareNote(note: Note) {
        val message = "Header: ${note.header}\nMessage: ${note.message}\nDate: ${note.date}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        val chosenIntent = Intent.createChooser(intent, "Share with:")
        startActivity(chosenIntent)
    }

}

//    private fun returnToLoginScreen(){
//        parentFragmentManager.beginTransaction()
//                  .replace(R.id.fragmentContainerView, RegistrationFragment(),"RegistrationFragment")
//                  .commit()
//    }
//
//    private fun makeNewNote(){
//        parentFragmentManager.beginTransaction()
//                  .replace(R.id.fragmentContainerView, viewModel!!.toNextScreen(listOfNotes),"NewNoteFragment")
//                  .commit()
//    }