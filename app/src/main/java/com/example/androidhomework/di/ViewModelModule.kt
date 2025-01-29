package com.example.androidhomework.di

import com.example.androidhomework.presentation.view_model.MainFragmentViewModel
import com.example.androidhomework.presentation.view_model.RegistrationFragmentViewModel
import com.example.androidhomework.presentation.view_model.NewNoteFragmentViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module{
    singleOf(::RegistrationFragmentViewModel)
    singleOf(::MainFragmentViewModel)
    singleOf(::NewNoteFragmentViewModel)
}