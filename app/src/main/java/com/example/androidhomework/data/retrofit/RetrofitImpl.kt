package com.example.androidhomework.data.retrofit

import com.example.androidhomework.data.api.Server
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {

    val api: Server by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Server::class.java)
    }

}