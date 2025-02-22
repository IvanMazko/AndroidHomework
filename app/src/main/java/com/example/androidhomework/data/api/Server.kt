package com.example.androidhomework.data.api

import com.example.androidhomework.data.objects.AllCharacters
import com.example.androidhomework.data.objects.AllCharactersResponse
import com.example.androidhomework.data.objects.CharacterResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Server {
    @GET("/character")
    suspend fun getAllCharacters() : AllCharacters
    @GET("/character/{objectId}")
    suspend fun getCurrentCharacterById(@Path("objectId") objectId : Int) : CharacterResponse
    @GET("/character")
    suspend fun getCharacterByName(@Query("name") name: String): AllCharactersResponse

}