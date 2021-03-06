package com.rp.hitta.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.choota.dev.ctimeago.TimeAgo
import com.rp.hitta.R
import com.rp.hitta.model.Review
import com.rp.hitta.util.ViewUtils
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
        holder.populate(review, null)
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
        private val userIcon: ImageView = view.findViewById(R.id.review_user_icon)
        private val userName: TextView = view.findViewById(R.id.review_user_name)
        private val ratingBar: RatingBar = view.findViewById(R.id.review_rating)
        private val reviewTimeAndPlace: TextView = view.findViewById(R.id.review_time_and_place)
        private val reviewContent: TextView = view.findViewById(R.id.review_content)

        fun populate(review: Review, clickListener: View.OnClickListener?) {
            val context = userIcon.context
            ViewUtils.loadImage(userIcon, review.avatar)
            userName.text = if (TextUtils.isEmpty(review.userName)) context.getString(R.string.default_user_name) else review.userName
            ratingBar.rating = review.rating.toFloat()
            val timeAgo = TimeAgo().locale(context).getTimeAgo(Date(review.createdAt))
            reviewTimeAndPlace.text = context.getString(R.string.concat_w_dash_frm, timeAgo, review.source)
            reviewContent.setOnClickListener(clickListener)
            if (review.isCurrentUser == 1 && TextUtils.isEmpty(review.content)) {
                reviewContent.setText(R.string.describe_your_experience)
                reviewContent.setTextColor(ContextCompat.getColor(context, R.color.hitta_blue))
            } else {
                reviewContent.text = review.content
                reviewContent.setTextColor(ContextCompat.getColor(context, R.color.textColorPrimary))
            }
        }
    }
}