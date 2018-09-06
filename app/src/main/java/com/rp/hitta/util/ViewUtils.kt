package com.rp.hitta.util

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rp.hitta.R
import com.rp.hitta.db.ReviewsDatabase
import com.rp.hitta.model.Review


/**
 * Created by roba
 */
class ViewUtils {
    companion object {
        fun loadImage(targeImv: ImageView, url: String?) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_account_unknown)
            requestOptions.error(R.drawable.ic_account_unknown)
            Glide.with(targeImv.context).setDefaultRequestOptions(requestOptions).load(url).into(targeImv)
        }

        fun updateCompanyStats(reviewsCompanyRating: TextView?, reviewsFromRatings: TextView?, reviews: List<Review>?) {
            val context = reviewsCompanyRating!!.context
            var reviewsSum = 0
            var reviewsCount = 0
            val ownRating = ReviewsDatabase.getInstance(context).reviewsDao().getOwnReview()

            if (reviews == null || reviews.isEmpty()) {
                reviewsSum = ownRating?.rating ?: 0
                reviewsCount = if (ownRating == null) 0 else 1
            } else {
                for (review in reviews) {
                    reviewsSum += review.rating
                }
                reviewsSum += ownRating?.rating ?: 0
                reviewsCount = reviews.size + if (ownRating == null || ownRating.rating == 0) 0 else 1
            }
            reviewsCount = Math.max(reviewsCount, 1)//prevent division by 0
            reviewsCompanyRating.text = (reviewsSum.toFloat() / reviewsCount).toString()
            reviewsFromRatings!!.text = context.getString(R.string.from_frm_ratings, reviewsCount)
        }
    }
}