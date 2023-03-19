package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class movieDetial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detial)

        val moviePosterIV = findViewById<ImageView>(R.id.moviePosterIV)
        val movieTitleTv = findViewById<TextView>(R.id.movieTitleTv)
        val popularityRB = findViewById<RatingBar>(R.id.ratingRB)
        var releaseDateTV = findViewById<TextView>(R.id.releadDateTV)
        val overviewTV = findViewById<TextView>(R.id.overviewTV)

        // Get the extra from the Intent-0
        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        // Load image from url
        val radius = 30  // corner radius, higher value = more rounded
        Glide.with(this)
            .load(movie.posterImageURL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.image_not_available)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(moviePosterIV)

        movieTitleTv.text = movie.title
        overviewTV.text = movie.overview
        releaseDateTV.text = "Released Date: " + movie.releaseDate

        popularityRB.rating = (movie.rating!!/2.0).toFloat()
        //Log.e("rating", popularityRB.rating.toString())
    }
}