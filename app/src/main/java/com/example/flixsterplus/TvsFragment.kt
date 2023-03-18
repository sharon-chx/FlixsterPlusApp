package com.example.flixsterplus

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers


// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"


/*
 * The class for the only fragment in the app, which contains the
 * recyclerView, and performs the network calls to The Movie Database API.
 */
class TvsFragment: Fragment(), OnListFragmentInteractionListener {

    private lateinit var tvs: List<TV>

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
        recyclerView.layoutManager = LinearLayoutManager(context)
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

                        //Log.e("data", json?.jsonObject?.get("results").toString())

                        //get json data and convert to String
                        val moviesRawJSON = json?.jsonObject?.get("results").toString()


                        // convert json data to Movie objects, then create an adapter based on the Movie List
                        val gson = Gson()
                        val arrayTvType = object: TypeToken<List<TV>>(){}.type
                        tvs = gson.fromJson(moviesRawJSON, arrayTvType)


                        recyclerView.adapter = TvsRecyclerViewAdapter(tvs, this@TvsFragment)
                    }

                }
            ]

    }


    /*
    * Send the app to detail screen when item is clicked
     */
    override fun onItemClick(position: Int) {
        val tv = tvs[position]

        //Log.e("rating check 1:", tv.rating.toString())

        //  Navigate to Details screen and pass selected tv show
        val intent = Intent(context, TvDetail::class.java)
        intent.putExtra(TV_EXTRA, tv)
        val p1 = android.util.Pair(view?.findViewById(R.id.posterIV) as View, "poster")
        val p2 = android.util.Pair(view?.findViewById(R.id.titleTV) as View, "title")
        val p3 = android.util.Pair(view?.findViewById(R.id.descriptionTV) as View, "overview")
        val options = ActivityOptions.makeSceneTransitionAnimation(this.activity, p1, p2, p3)
        context?.startActivity(intent, options.toBundle())
    }


}

