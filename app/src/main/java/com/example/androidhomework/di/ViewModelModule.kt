package com.example.androidhomework.di

import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.SignInFragmentViewModel
import com.example.androidhomework.presentation.view_model.NewNoteFragmentViewModel
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module{
    singleOf(::SignInFragmentViewModel)
    singleOf(::MainFragmentViewModel)
    singleOf(::NewNoteFragmentViewModel)
    singleOf(::RegistrationFragmentViewModel)
}