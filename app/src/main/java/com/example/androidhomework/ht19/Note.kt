package com.example.androidhomework.ht19

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed interface Custom : Parcelable{
    @Parcelize
    class Note (
        var header : String,
        val message : String,
        val date : String,
    ) : Parcelable, Custom

    @Parcelize
    class Icon : Parcelable, Custom
}




