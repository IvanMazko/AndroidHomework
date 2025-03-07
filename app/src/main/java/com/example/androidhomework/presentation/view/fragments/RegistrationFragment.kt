package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.androidhomework.R
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.data.storage.room.DatabaseProvider
import com.example.androidhomework.data.storage.room.MyDatabase
import com.example.androidhomework.data.storage.room.UserDao
import com.example.androidhomework.presentation.actions.RegistrationFragmentActions
import com.example.androidhomework.presentation.actions.SignInFragmentActions
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {

    private val viewModel : RegistrationFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_registration, container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.userDao()

        // creating editTexts buttons
        val login = view.findViewById<AppCompatEditText>(R.id.ar_username_et)
        val password = view.findViewById<AppCompatEditText>(R.id.ar_password_et)
        val logInBtn = view.findViewById<AppCompatButton>(R.id.ar_log_in_btn)
        val returnToSignInScreenBtn = view.findViewById<AppCompatButton>(R.id.ar_return_btn)
        initClickListeners(logInBtn, returnToSignInScreenBtn, login, password, userPrefs, dao)

        observeViewModel()
    }


    private fun observeViewModel(){
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.toMainScreenBtn){
                    val mainFragment = MainFragment()
                    mainFragment.arguments = it.userName
                    toNextScreen(mainFragment, "MainFragment")
                }
                if (it.toSignInScreenBtn){
                    toNextScreen(SignInFragment(), "SignInFragment")
                }
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
    }

    private fun initClickListeners(logInBtn : AppCompatButton, signInBtn : AppCompatButton, login : AppCompatEditText, password : AppCompatEditText, userPrefs : UserPreferences, dao: UserDao){
        logInBtn.setOnClickListener {
            if (login.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (dao.getUserByUsername(login.text.toString()) == null){
                        val job = launch(Dispatchers.IO) {
                            viewModel.handleAction(RegistrationFragmentActions.RegisterUser(login.text.toString(), password.text.toString(), dao))
                        }
                        job.join() // Дожидаемся завершения вставки
                        val user = getUserFromDb(login.text.toString(), dao)
                        if (user != null) {
                            userPrefs.saveUser(user)
                            viewModel.handleAction(RegistrationFragmentActions.GoToMainScreen(login.text.toString()))
                        }
                    }
                    else {
                        Toast.makeText(requireContext(), "The user with this login already exists. Think of a different login.", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
        signInBtn.setOnClickListener {
            viewModel.handleAction(RegistrationFragmentActions.GoToSignInScreen)
        }
    }

    private suspend fun getUserFromDb(login: String, dao: UserDao) :  User? {
        return withContext(Dispatchers.IO) {
            dao.getUserByUsername(login) // Получаем пользователя по логину
        }
    }
}