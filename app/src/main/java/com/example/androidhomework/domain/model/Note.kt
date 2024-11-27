package com.example.androidhomework.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note (
    var header : String,
    val message : String,
    val date : String,
) : Parcelable



