package com.example.flixsterplus

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Result(
    @SerialName("results")
    var results: List<Movie>?
)


@Keep
@Serializable
data class Movie (

    @SerialName("poster_path")
    var posterImageURL: String? = null,

    @SerialName("title")
    var title: String? = null,

    @SerialName("overview")
    var description: String? = null,

    @SerialName("release_date")
    var releaseDate: String? = null,
)





