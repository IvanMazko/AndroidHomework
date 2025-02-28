package com.example.androidhomework.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note (
    @PrimaryKey val id: Int,
    @ColumnInfo val header : String,
    @ColumnInfo val message : String,
    val date : String
) : Parcelable



