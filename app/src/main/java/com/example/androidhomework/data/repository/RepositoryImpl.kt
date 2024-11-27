package com.example.androidhomework.data.repository

import com.example.androidhomework.data.api.NetworkApi
import com.example.androidhomework.domain.repository.Repository

class RepositoryImpl(
    private val networkApi: NetworkApi
) : Repository {
    override fun save(){
        networkApi.save()
    }

    override fun put() {
        networkApi.put()
    }
}