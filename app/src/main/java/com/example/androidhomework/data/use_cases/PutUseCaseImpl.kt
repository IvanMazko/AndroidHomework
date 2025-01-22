package com.example.androidhomework.data.use_cases

import com.example.androidhomework.domain.repository.Repository
import com.example.androidhomework.domain.use_cases.PutUseCase

class PutUseCaseImpl (
    private val repository: Repository
): PutUseCase {
    override fun invoke(){
        repository.put()
    }
}