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
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel

class RegistrationFragment : Fragment() {

    private var viewModel: RegistrationFragmentViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(RegistrationFragmentViewModel::class.java)
    }
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
            viewModel?.checkData(login.text.toString(), password.text.toString())

        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel?.publicLiveData?.observe(viewLifecycleOwner){ newData ->
            if (newData != null){
                toNewScreen(newData)
            }
            else{
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun toNewScreen(newData:Bundle){
        val mainFragment = MainFragment()
        mainFragment.arguments = newData
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, mainFragment,"MainFragment")
            .commit()
    }
}