package com.example.androidhomework.ht19

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Note (
    var header : String,
    val message : String,
    val date : String,
) : Parcelable



