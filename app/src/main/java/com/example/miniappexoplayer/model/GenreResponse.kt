package com.example.miniappexoplayer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
): Parcelable

@Parcelize
data class Genre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
): Parcelable