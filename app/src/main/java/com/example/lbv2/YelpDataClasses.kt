package com.example.lbv2

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

data class YelpRestaurant(
    // When using exact extraction keywords that are in the JSON
    // we can simply initialize them this this
    val name: String,
    val rating: Double,
    val price: String,
    // This is how we'd create local keywords
    @SerializedName("review_count") val numReviews: Int,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String,
    // Access our classes that gather partial data
    val categories: List<YelpCategory>,
    val location: YelpLocation
){
    // Returns distance in miles
    fun displayDistance(): String {
        val milesPerMeter = 0.000621371
        val distanceInMiles = "%2f".format(distanceInMeters * milesPerMeter)
        return "$distanceInMiles miles away"
    }
}

// Create new class to parse partial (specific) data in item
data class YelpCategory(
    val title: String
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)