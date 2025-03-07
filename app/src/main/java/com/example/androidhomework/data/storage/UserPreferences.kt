package com.example.androidhomework.data.storage

import android.content.Context

import androidx.core.content.edit
import com.example.androidhomework.data.model.User
import com.google.gson.Gson

class UserPreferences(context: Context) {

    private val prefsName = "MY_SHARED_PREFERENCES"

    private val sharedPrefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUser(user: User){
        val userJson = gson.toJson(user)
        sharedPrefs.edit {
            putString("current_user", userJson).apply()
        }
    }

    fun getUser() : User?{
        val userJson = sharedPrefs.getString("current_user", null)
        return if (userJson != null){
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }

    }

    fun isUserExists() : Boolean{
        return sharedPrefs.contains("current_user")
    }


    fun deleteUser(){
        sharedPrefs.edit{
            remove("current_user").apply()
        }
    }



}