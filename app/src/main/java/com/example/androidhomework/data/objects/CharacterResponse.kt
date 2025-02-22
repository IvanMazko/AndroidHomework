package com.example.androidhomework.data.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
@SerializedName("data") val character: CurrentCharacter
) : Parcelable