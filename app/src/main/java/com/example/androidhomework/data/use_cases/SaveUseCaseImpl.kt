package com.example.androidhomework.data.use_cases

import com.example.androidhomework.domain.repository.Repository
import com.example.androidhomework.domain.use_cases.SaveUseCase

class SaveUseCaseImpl (
    private val repository: Repository
) : SaveUseCase{
    override fun invoke(){
        repository.save()
    }
}