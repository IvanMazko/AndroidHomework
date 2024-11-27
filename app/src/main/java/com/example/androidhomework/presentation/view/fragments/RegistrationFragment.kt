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
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(RegistrationFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_registration, container, false)
        return currentView
    }

    companion object{
        fun newInstance(login: AppCompatEditText): MainFragment {
            val mainFragment = MainFragment()
            val args = Bundle()
            args.putString("username", login.text.toString())
            mainFragment.arguments = args
            return mainFragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = view.findViewById<AppCompatEditText>(R.id.login_et)
        val password = view.findViewById<AppCompatEditText>(R.id.password_et)
        val button = view.findViewById<AppCompatButton>(R.id.sign_up_btn)

        button.setOnClickListener {
            if (login.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, newInstance(login),"MainFragment")
                    .commit()
            }
        }

        viewModel?.publicLiveData?.observe(this.viewLifecycleOwner) {}
    }
}