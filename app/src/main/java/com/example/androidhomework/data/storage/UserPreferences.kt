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
            putString(user.login, userJson).apply()
        }
    }

    fun getUserByLogin(login: String) : User?{
        val userJson = sharedPrefs.getString(login, null)
        return if (userJson != null){
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }

    }
    fun isUserExists(login:String) : Boolean{
        return sharedPrefs.contains(login)
    }

    fun isUserValid(login: String, password : String) : Boolean{
        val user = getUserByLogin(login)
        return user?.password == password
    }


}