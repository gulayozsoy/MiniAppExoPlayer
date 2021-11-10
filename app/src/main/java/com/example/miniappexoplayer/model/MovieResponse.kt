package com.example.miniappexoplayer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  MovieResponse(
    @SerializedName("results") val result: List<Movies>?
): Parcelable

@Parcelize
open class Movies(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("original_title")
    val title: String?,
    val popularity : Double?,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("release_date")
    val releaseDate : String?,
    val id: Int
): Parcelable

