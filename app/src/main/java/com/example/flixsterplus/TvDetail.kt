package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TvDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvPosterIV = findViewById<ImageView>(R.id.tvPosterIV)
        val tvTitleTv = findViewById<TextView>(R.id.tvTitleTv)
        val popularityRB = findViewById<RatingBar>(R.id.ratingRB)
        var countryTV = findViewById<TextView>(R.id.countryTV)
        val overviewTV = findViewById<TextView>(R.id.overviewTV)

        // Get the extra from the Intent-0
        val tv = intent.getSerializableExtra(TV_EXTRA) as TV

        // Load image from url
        val radius = 30  // corner radius, higher value = more rounded
        Glide.with(this)
            .load(tv.posterImageURL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.image_not_available)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(tvPosterIV)

        tvTitleTv.text = tv.title
        overviewTV.text = tv.overview
        countryTV.text = "Origin country: " + tv.country?.joinToString(", ")

        popularityRB.rating = (tv.rating!!/2.0).toFloat()
        Log.e("rating", popularityRB.rating.toString())


    }
}