package com.example.flixsterplus

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * [RecyclerView.Adapter] that can display a [Movies] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecylerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<MoviesRecylerViewAdapter.ViewHolder>()
    {
    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val posterIv = itemView.findViewById<ImageView>(R.id.posterIV)
        val titleTv = itemView.findViewById<TextView>(R.id.titleTV)
    }

    // create the layout of the page
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_segment, parent, false)
        // Return a new holder instance
        return ViewHolder(view)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val movie :Movie = movies.get(position)
        // Set item views based on your views and data model
        holder.titleTv.text = movie.title
        movie.posterImageURL = "https://image.tmdb.org/t/p/w500/" + movie.posterImageURL
        Log.e("image", movie.posterImageURL!!)
        // Load image from url
        Glide.with(holder.itemView)
            .load(movie.posterImageURL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.image_not_available)
            .centerInside()
            .into(holder.posterIv)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}