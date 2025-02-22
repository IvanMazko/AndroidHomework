package com.example.androidhomework.data.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentCharacter(
    @SerializedName("_id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("films") val films : ArrayList<String>
) : Parcelable