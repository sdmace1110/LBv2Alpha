package com.example.lbv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Variable to hold Yelp base URL
private const val BASE_URL = "https://api.yelp.com/v3/"
// Add a log statement
private const val TAG = "MainActivity"
// API Key
private const val API_KEY = "33gfsU_pO7P4h6NsxV75EHhzMSivbTpkrUMae0fn3cpAjvS61hR3JiFmTf7g-FEXxgJaTWqoXebTuMWqTlWVRf9pX_H8IiUEi1_sxh1UxhwhrO_8GRcSbZ8LXK2VYHYx"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Signal the recycler view, store
        val restaurants = mutableListOf<YelpRestaurant>()
        // Create adapter view: takes a reference and list of restaurants
        val adapter = RestaurantsAdapter(this, restaurants)

        // Recycler view (rv____) requirements and use
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)

        // Build and deploy Retrofit
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val yelpService = retrofit.create(YelpService::class.java)
        // Authorize Yelp with search data
        yelpService.searchRestaurants("Bearer $API_KEY",
            "steak",
            "Nashville").enqueue(object : Callback<YelpSearchResult> {

            // If HTTP requests fail
            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                // Label the log which prints to Logcat
                Log.i(TAG, "onFailure $t")
            }
            // If they pass
            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>
            ) {
                // Label the log to examine in Logcat
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if(body == null){
                    Log.w(TAG, "Did not receive the data from Yelp API... exiting")
                    return
                }
                // Add the data from Yelp and notify adapter there is a change in state
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }



            })
    }
}
