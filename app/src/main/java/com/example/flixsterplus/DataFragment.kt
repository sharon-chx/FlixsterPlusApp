package com.example.flixsterplus

import android.app.ActivityOptions
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
private const val API_KEY = ""


/*
 * The class for the only fragment in the app, which contains the
 * recyclerView, and performs the network calls to The Movie Database API.
 */
class DataFragment: Fragment(), OnListFragmentInteractionListener {

    private lateinit var tvs: List<TV>
    private lateinit var movies: List<Movie>

    /*
     * Constructing the view
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Lookup the recyclerview in movie_fragment_list layout
        val view = inflater.inflate(R.layout.activity_main, container, false)
        val tvRecyclerView = view.findViewById<RecyclerView>(R.id.tvsRV)
        val movieRecyclerView = view.findViewById<RecyclerView>(R.id.moviesRV)

        // Set layout manager to position the movies
        val context = view.context

        // Set horizontal layout
        val layoutManager1 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvRecyclerView.layoutManager = layoutManager1
        movieRecyclerView.layoutManager = layoutManager2

        updateAdapter(tvRecyclerView, movieRecyclerView)

        val tvSection = view.findViewById<TextView>(R.id.tvSection)
        tvSection.visibility = View.INVISIBLE

        val movieSection = view.findViewById<TextView>(R.id.movieSection)
        movieSection.visibility = View.INVISIBLE

        return view
    }


    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(tvRecyclerView: RecyclerView, movieRecyclerView: RecyclerView) {

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

                        //Log.e("data", json?.jsonObject?.get("results").toString())

                        //get json data and convert to String
                        val tvsRawJSON = json?.jsonObject?.get("results").toString()


                        // convert json data to Movie objects, then create an adapter based on the Movie List
                        val gson = Gson()
                        val arrayTvType = object: TypeToken<List<TV>>(){}.type
                        tvs = gson.fromJson(tvsRawJSON, arrayTvType)


                        tvRecyclerView.adapter = TvsRecyclerViewAdapter(tvs, this@DataFragment)
                    }

                }
            ]


        client["https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY,
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

                        //Log.e("data", json?.jsonObject?.get("results").toString())

                        //get json data and convert to String
                        val moviesRawJSON = json?.jsonObject?.get("results").toString()


                        // convert json data to Movie objects, then create an adapter based on the Movie List
                        val gson = Gson()
                        val arrayMovieType = object: TypeToken<List<Movie>>(){}.type
                        movies = gson.fromJson(moviesRawJSON, arrayMovieType)


                        movieRecyclerView.adapter = MoviesRecyclerViewAdapter(movies, this@DataFragment)
                    }

                }
        ]

    }


    /*
    * Send the app to detail screen when item is clicked
     */
    override fun onTVItemClick(position: Int) {

        val tv = tvs[position]
        //Log.e("rating check 1:", tv.rating.toString())

        //  Navigate to Details screen and pass selected tv show
        val intent = Intent(context, tvDetail::class.java)
        intent.putExtra(TV_EXTRA, tv)
        val p1 = android.util.Pair(view?.findViewById(R.id.posterIV) as View, "poster")
        val p2 = android.util.Pair(view?.findViewById(R.id.titleTV) as View, "title")
        val options = ActivityOptions.makeSceneTransitionAnimation(this.activity, p1, p2)
        context?.startActivity(intent, options.toBundle())
    }

    override fun onMovieItemClick(position: Int) {

        val movie = movies[position]
        //Log.e("rating check 1:", tv.rating.toString())

        //  Navigate to Details screen and pass selected tv show
        val intent = Intent(context, movieDetial::class.java)
        intent.putExtra(MOVIE_EXTRA, movie)
        val p1 = android.util.Pair(view?.findViewById(R.id.posterIV) as View, "poster")
        val p2 = android.util.Pair(view?.findViewById(R.id.titleTV) as View, "title")
        val options = ActivityOptions.makeSceneTransitionAnimation(this.activity, p1, p2)
        context?.startActivity(intent, options.toBundle())
    }


}

