package com.example.flixsterplus

import android.view.View

/**
 * This interface is used by the [MoviesRecyclerViewAdapter] to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by [MoviesFragment]
 */
interface OnListFragmentInteractionListener {
    fun onItemClick(position: Int)
}