package com.example.lbv2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

public interface YelpService{
    // HTTP requests
    @GET( "businesses/search" )
    fun searchRestaurants(
        @Header("Authorization") authHeader: String,
        @Query("terms") searchTerm: String,
        @Query("location") location: String) : Call<YelpSearchResult>
}