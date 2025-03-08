package com.example.androidhomework.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidhomework.R
import com.example.androidhomework.data.storage.UserPreferences
import com.example.androidhomework.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding : ActivitySplashBinding ?= null
    private val binding : ActivitySplashBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivitySplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPreferences(requireContext())

        lifecycleScope.launch {
            delay(3000)
            if (checkUser(userPrefs)){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MainFragment(),"RegistrationFragment")
                    .commit()
            }
            else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, SignInFragment(),"RegistrationFragment")
                    .commit()
            }

        }
    }

    private fun checkUser(userPrefs: UserPreferences): Boolean {
        return userPrefs.isUserExists()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}