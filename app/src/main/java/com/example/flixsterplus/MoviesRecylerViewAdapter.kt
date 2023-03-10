package com.example.flixsterplus


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesRecylerViewAdapter(private val items: List<Movie>) : RecyclerView.Adapter<MoviesRecylerViewAdapter.ViewHolder>(){

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val posterIv = itemView.findViewById<ImageView>(R.id.posterIV)
        val titleTv = itemView.findViewById<TextView>(R.id.titleTV)
        val descrTv = itemView.findViewById<TextView>(R.id.descriptionTV)
    }

    // create the layout of the page
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val movieView = inflater.inflate(R.layout.movie_segment, parent, false)
        // Return a new holder instance
        return ViewHolder(movieView)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on your views and data model
        holder.descrTv.text = item.description
        holder.titleTv.text = item.title
        // pending: image
    }

    override fun getItemCount(): Int {
        return items.size
    }
}