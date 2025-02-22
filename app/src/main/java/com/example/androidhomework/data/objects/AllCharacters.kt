package com.example.androidhomework.data.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllCharacters(
    @SerializedName("data") val data : ArrayList<CurrentCharacter>
): Parcelable