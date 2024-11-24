package com.example.androidhomework.ht21

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import com.example.androidhomework.ht18.RegistrationActivity

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.activity_splash, container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RegistrationFragment(),"RegistrationFragment")
                .commit()
        }, 3000)
    }
}