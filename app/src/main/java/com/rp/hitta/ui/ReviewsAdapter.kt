package com.rp.hitta.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.choota.dev.ctimeago.TimeAgo
import com.rp.hitta.R
import com.rp.hitta.model.Review
import java.util.*

/**
 * Created by roba
 */
class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.RecyclerViewHolder>() {
    private var reviewsList: MutableList<Review> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.review_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val review = reviewsList[position]
        val context = holder.userIcon.context
        Glide.with(context).load(review.avatar).into(holder.userIcon)
        holder.userName.text = review.userName
        holder.ratingBar.rating = review.rating.toFloat()
        holder.reviewContent.text = review.content
        holder.reviewTimeAndPlace.text = context.getString(R.string.concat_w_dash_frm, TimeAgo().getTimeAgo(Date(review.createdAt)), review.source)
    }

    override fun getItemCount(): Int {
        return reviewsList.size
    }

    fun updateReviews(reviews: List<Review>?) {
        this.reviewsList.clear()
        reviews?.let { this.reviewsList.addAll(it) }
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userIcon: ImageView = view.findViewById(R.id.review_user_icon)
        val userName: TextView = view.findViewById(R.id.review_user_name)
        val ratingBar: RatingBar = view.findViewById(R.id.review_rating)
        val reviewTimeAndPlace: TextView = view.findViewById(R.id.review_time_and_place)
        val reviewContent: TextView = view.findViewById(R.id.review_content)
    }
}