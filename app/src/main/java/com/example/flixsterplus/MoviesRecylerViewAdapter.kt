package com.example.flixsterplus


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] that can display a [Movies]
 */
class MoviesRecylerViewAdapter(
    private val movies: List<Movie>)
    : RecyclerView.Adapter<MoviesRecylerViewAdapter.ViewHolder>()
    {
    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // PENDING: val posterIv = itemView.findViewById<ImageView>(R.id.posterIV)
        val titleTv = itemView.findViewById<View>(R.id.titleTV) as TextView
        val descrTv = itemView.findViewById<View>(R.id.descriptionTV) as TextView
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
        holder.descrTv.text = movie.description
        holder.titleTv.text = movie.title
        // pending: image
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}