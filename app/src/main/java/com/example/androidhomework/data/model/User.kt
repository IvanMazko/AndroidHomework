package com.example.androidhomework.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val username : String,
    @ColumnInfo var password : String
)