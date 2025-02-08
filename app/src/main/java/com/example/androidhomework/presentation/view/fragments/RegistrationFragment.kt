package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.androidhomework.R
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationFragmentViewModel by viewModels()

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

        // creating editTexts and a button
        val login = view.findViewById<AppCompatEditText>(R.id.login_et)
        val password = view.findViewById<AppCompatEditText>(R.id.password_et)
        val button = view.findViewById<AppCompatButton>(R.id.sign_up_btn)

        //transiting to the next fragment
        button.setOnClickListener {
            if (login.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                val mainFragment = MainFragment()
                mainFragment.arguments = viewModel.toNextScreen(login.text.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, mainFragment,"MainFragment")
                    .commit()
            }
        }
        
    }
}