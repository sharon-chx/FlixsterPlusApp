package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterplus.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import okhttp3.Headers

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the
 * recyclerView, and performs the network calls to The Movie Database API.
 */
class MoviesFragment: Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Lookup the recyclerview in movie_fragment_list layout
        val view = inflater.inflate(R.layout.movie_fragment_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.moviesRV)

        // Set layout manager to position the movies
        val context = view.context
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        updateAdapter(progressBar, recyclerView)
        return view
    }


    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() and RequestParams here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        // Using the client, perform the HTTP request
        client["https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY,
                params,
                object: JsonHttpResponseHandler(){
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        if (response != null) {
                            Log.e("failure", response)
                        }
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                        progressBar.hide()

                        val movies: MutableList<Movie> = mutableListOf()

                        val parsedJson = Json{ignoreUnknownKeys = true}.decodeFromString<Result>(
                            json?.jsonObject.toString()
                        )

                        parsedJson.results?.let { list ->
                            movies.addAll(list)
                        }

                        recyclerView.adapter = MoviesRecylerViewAdapter(movies, this@MoviesFragment)
                    }

                }
            ]

    }

    /*
     * What happens when a particular movie is clicked.
     */
    override fun onItemClick(movie: Movie) {
        Toast.makeText(context, "test: " + movie.title, Toast.LENGTH_LONG).show()
    }

}