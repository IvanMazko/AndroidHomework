package com.example.androidhomework.presentation.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhomework.R
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.presentation.actions.SignInFragmentActions
import com.example.androidhomework.presentation.view_model.SignInFragmentViewModel

class SignInFragment : Fragment() {

    private val viewModel: SignInFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.activity_sign_in, container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPrefs = UserPreferences(requireContext())

        // creating editTexts and buttons
        val login = view.findViewById<AppCompatEditText>(R.id.login_et)
        val password = view.findViewById<AppCompatEditText>(R.id.password_et)
        val signInBtn = view.findViewById<AppCompatButton>(R.id.sign_in_btn)
        val registerBtn = view.findViewById<AppCompatButton>(R.id.asi_register_btn)
        initClickListeners(signInBtn, registerBtn, login, password, userPrefs)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.toMainScreenBtn) {
                    val mainFragment = MainFragment()
                    mainFragment.arguments = it.userName
                    toNextScreen(mainFragment, "MainFragment")
                }
                if (it.toRegisterScreenBtn) {
                    toNextScreen(RegistrationFragment(), "RegistrationFragment")
                }
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
    }

    private fun initClickListeners(signInBtn : AppCompatButton, registerBtn : AppCompatButton, login : AppCompatEditText, password : AppCompatEditText, userPrefs : UserPreferences){
        signInBtn.setOnClickListener {
            if (login.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else if (userPrefs.isUserValid(login.text.toString(), password.text.toString())){
                viewModel.handleAction(SignInFragmentActions.GoToMainScreen(login.text.toString()))
            }
            else {
                Toast.makeText(requireContext(), "Invalid login or password!", Toast.LENGTH_SHORT).show()
            }

        }
        registerBtn.setOnClickListener {
            viewModel.handleAction(SignInFragmentActions.GoToRegistrationScreen)
        }
    }
}
