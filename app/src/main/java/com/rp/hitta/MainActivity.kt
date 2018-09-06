package com.rp.hitta

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RatingBar
import com.rp.hitta.api.ReviewsViewModel
import com.rp.hitta.init.Initializer
import com.rp.hitta.model.Review
import com.rp.hitta.ui.ReviewsAdapter
import com.rp.hitta.util.ViewUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by roba
 */
class MainActivity : AppCompatActivity() {
    companion object {
        private const val KEY_COMPANY_NAME = "KEY_COMPANY_NAME"
        fun intentFor(context: Context, companyName: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_COMPANY_NAME, companyName)
            return intent
        }
    }

    private lateinit var reviewsViewModel: ReviewsViewModel
    private lateinit var companyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        companyName = intent.getStringExtra(KEY_COMPANY_NAME)
        companyName.let { companyNameTv.text = it }

        val adapter = ReviewsAdapter()
        reviewsRecyclerView.adapter = adapter

        reviewsViewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
        reviewsViewModel.getReviewsList().observe(this, Observer<List<Review>> {
            ViewUtils.updateCompanyStats(reviewsCompanyRating, reviewsFromRatings, it)
            adapter.updateReviews(it)
        })
    }

    override fun onResume() {
        super.onResume()
        refreshOwnReview()
    }

    private fun refreshOwnReview() {
        val ownReview: Review = reviewsViewModel.getOwnReview() ?: Initializer.getOwnDefaultReview()
        ownReviewContainer.removeAllViews()
        if (ownReview.rating == 0) {//rating 0 means own review was not yet given
            yourReviewLbl.setVisibility(View.GONE)
            val placeholderLayout = LayoutInflater.from(this).inflate(R.layout.own_review_placeholder, ownReviewContainer, false)
            placeholderLayout.findViewById<RatingBar>(R.id.ownRating).onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, p1, _ ->
                        CreateReviewActivity.start(this@MainActivity, companyName, p1.toInt())
                    }
            ownReviewContainer.addView(placeholderLayout)
        } else {
            yourReviewLbl.setVisibility(View.VISIBLE)
            val ownReviewLayout = LayoutInflater.from(this).inflate(R.layout.review_item, ownReviewContainer, false)
            ReviewsAdapter.RecyclerViewHolder(ownReviewLayout).populate(ownReview, View.OnClickListener {
                CreateReviewActivity.start(this, companyName, ownReview.rating, ownReview.userName, ownReview.content)
            })
            ownReviewContainer.addView(ownReviewLayout)
        }
    }
}