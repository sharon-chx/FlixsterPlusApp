package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

const val TV_EXTRA = "TV_EXTRA"

/**
 * [RecyclerView.Adapter] that can display a [Movies] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class TvsRecyclerViewAdapter(
    private val tvs: List<TV>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<TvsRecyclerViewAdapter.ViewHolder>()
    {

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val posterIv = itemView.findViewById<ImageView>(R.id.posterIV)
        val titleTv = itemView.findViewById<TextView>(R.id.titleTV)
    }

    // create the layout of the page
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.segment, parent, false)
        // Return a new holder instance
        return ViewHolder(view)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val tv :TV = tvs.get(position)
        // Set item views based on your views and data model
        holder.titleTv.text = tv.title
        tv.posterImageURL = "https://image.tmdb.org/t/p/w500/" + tv.posterImageURL
        // Load image from url
        val radius = 30  // corner radius, higher value = more rounded
        Glide.with(holder.itemView)
            .load(tv.posterImageURL)
            .placeholder(R.drawable.loading)
            .error(R.drawable.image_not_available)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(holder.posterIv)

        // set up onClickListener
        holder.itemView.setOnClickListener{
            mListener?.onTVItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }
}