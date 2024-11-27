package com.example.androidhomework.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidhomework.R
import com.example.androidhomework.databinding.ActivityMainBinding
import com.example.androidhomework.presentation.view.fragments.SplashFragment
import com.example.androidhomework.presentation.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)  .create(ViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(R.layout.new_activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, SplashFragment(), "SplashFragment")
                .commit()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}