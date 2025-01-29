package com.example.androidhomework.presentation.view

import android.app.Application
import com.example.androidhomework.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        koinInitialization()
    }

    private fun koinInitialization(){
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}