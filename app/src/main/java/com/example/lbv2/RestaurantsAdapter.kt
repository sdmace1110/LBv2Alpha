package com.example.lbv2

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_restaurant.view.*



class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    // Provides signature for the adapter
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>(){

    // Inflate a new layout, 1 row in recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    // Size of restaurants list
    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Grab restaurant at this position
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(restaurant: YelpRestaurant) {
            itemView.tvName.text = restaurant.name
            itemView.ratingBar.rating = restaurant.rating.toFloat()
            itemView.tvNumReviews.text = "${restaurant.numReviews} Reviews"
            itemView.tvAddress.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            itemView.tvDistance.text = restaurant.displayDistance()
            itemView.tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).into(itemView.imageView)
        }

    }
}
