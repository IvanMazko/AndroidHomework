package com.example.androidhomework.data.storage.room

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: MyDatabase? = null

    fun getDatabase(context: Context): MyDatabase{
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "my_database")
                .fallbackToDestructiveMigration() // Удаляет старую БД при изменении схемы
                .build()
            INSTANCE = instance
            instance //return instance
        }
    }
}