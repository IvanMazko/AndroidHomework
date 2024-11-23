package com.example.androidhomework.ht19

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.parcelize.Parcelize

sealed interface Custom{
    @Parcelize
    class Note (
        var header : String,
        val message : String,
        val date : String,
    ) : Parcelable, Custom

    class Icon (
        val icon : AppCompatImageView
    ) : Custom
}




