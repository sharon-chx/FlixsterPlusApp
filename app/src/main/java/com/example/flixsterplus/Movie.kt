package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("poster_path")
    var posterImageURL: String? = null

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var description: String? = null

    init{
        posterImageURL = "https://image.tmdb.org/t/p/w500/" + posterImageURL
    }
}

