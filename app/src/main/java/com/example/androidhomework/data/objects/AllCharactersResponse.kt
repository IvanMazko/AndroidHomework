package com.example.androidhomework.data.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllCharactersResponse(
    @SerializedName("data") val characters: ArrayList<CurrentCharacter>
) : Parcelable