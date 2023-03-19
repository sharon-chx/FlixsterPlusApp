package com.example.flixsterplus

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable



@Keep
@Serializable
data class TV (
    @SerializedName("poster_path")
    var posterImageURL: String? = null,

    @SerializedName("name")
    var title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    var rating: Float? = null,

    @SerializedName("origin_country")
    var country: List<String>? = null
): java.io.Serializable


@Keep
@Serializable
data class Movie (
    @SerializedName("poster_path")
    var posterImageURL: String? = null,

    @SerializedName("name")
    var title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    var rating: Float? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null
): java.io.Serializable