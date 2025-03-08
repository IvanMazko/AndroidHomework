package com.example.androidhomework.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.data.storage.room.DatabaseProvider
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.databinding.ActivityMainBinding
import com.example.androidhomework.presentation.view.Adapter
import com.example.androidhomework.domain.model.Note
import com.example.androidhomework.presentation.actions.MainFragmentAction
import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private var listOfNotes: ArrayList<Note> = ArrayList()
    private var adapter: Adapter? = null

    private var _binding: ActivityMainBinding ?= null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connecting of DB and Prefs
        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.noteDao()

        // setting of username
        val currentUser = userPrefs.getUser()
        if (currentUser!=null){
            val username = currentUser.username
            viewModel.handleAction(MainFragmentAction.SetUserName(username))
        }


        //usage of Adapter
        val recyclerView = _binding?.amNotesRv
        adapter = Adapter(listOfNotes) { view, position: Int ->
            showPopupMenu(view, position, dao, currentUser)
        }
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())


        initClickListeners()

        // setting of notesList
        if (currentUser != null) {
            lifecycleScope.launch(Dispatchers.IO) {
                val updatedNotesList = dao.getNotesByUserId(currentUser.id)
                withContext(Dispatchers.Main) { // Переключаемся на главный поток перед обновлением LiveData
                    viewModel.handleAction(MainFragmentAction.SetNoteList(updatedNotesList))
                }
            }
        }

        observeViewModel(userPrefs)
    }

    private fun observeViewModel(userPrefs : UserPreferences) {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.signOutBtn) {
                    userPrefs.deleteUser()
                    toNextScreen(SignInFragment(), "RegistrationFragment")
                }
                if (it.addNewNoteBtn) {
                    val newNoteFragment = NewNoteFragment()
                    toNextScreen(newNoteFragment, "NewNoteFragment")
                }
                updateUserName(it.userNameTextView)
                updateNoteList(it.newNotesList)
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
    }

      private fun updateUserName(username: String) {
          _binding?.amUserNameActv?.text = username
      }

    private fun updateNoteList(noteList: List<Note>?) {
        if (noteList != null) {
            listOfNotes.clear()
            listOfNotes.addAll(noteList)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun initClickListeners() {
        _binding?.amAddNewNoteAcb?.setOnClickListener {
            viewModel.handleAction(MainFragmentAction.AddNewNote)
        }
        _binding?.amSignOutBtn?.setOnClickListener {
            viewModel.handleAction(MainFragmentAction.ReturnToRegistration)
        }
    }




    private fun showPopupMenu(view: View, position: Int, dao: NoteDao, currentUser : User?) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.note_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_delete -> {
                    deleteNote(listOfNotes, position, dao, currentUser)
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

    private fun deleteNote(listOfNotes: ArrayList<Note>, position: Int, dao: NoteDao, currentUser: User?) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao.deleteNoteById(listOfNotes[position].id)
            if (currentUser != null) {
                val updatedNoteList = dao.getNotesByUserId(currentUser.id)
                withContext(Dispatchers.Main){
                    viewModel.handleAction(MainFragmentAction.SetNoteList(updatedNoteList))
                }
            }
        }
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
