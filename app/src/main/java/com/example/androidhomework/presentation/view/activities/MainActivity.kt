package com.example.androidhomework.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.androidhomework.R
import com.example.androidhomework.databinding.ActivityMainBinding
import com.example.androidhomework.presentation.view.fragments.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, SplashFragment(), "SplashFragment")
                .commit()
        }

    }

}