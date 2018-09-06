package com.rp.hitta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.Toolbar
import com.rp.hitta.db.ReviewsDatabase
import com.rp.hitta.model.Review
import kotlinx.android.synthetic.main.create_review_layout.*
import kotlinx.android.synthetic.main.own_review_placeholder.*

/**
 * Created by roba
 */
class CreateReviewActivity : AppCompatActivity() {
    companion object {
        private const val KEY_COMPANY_NAME = "KEY_COMPANY_NAME"
        fun start(context: Context, companyName: String) {
            val intent = Intent(context, CreateReviewActivity::class.java)
            intent.putExtra(KEY_COMPANY_NAME, companyName)
            context.startActivity(intent)
        }
    }

    private lateinit var ownReview: Review
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_review_layout)

        ownReview = ReviewsDatabase.getInstance(this@CreateReviewActivity)!!.reviewsDao().getOwnReview()!!
        create_review_rating.rating = ownReview.rating.toFloat()
        updateRatingLabel(ownReview.rating)

        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.create_review_menu)
        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener, android.support.v7.widget.Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                if (isValidReview()) {
                    ownReview.userName = create_review_own_name.text.toString()
                    ownReview.content = create_review_own_review.text.toString()
                    ownReview.createdAt = System.currentTimeMillis()
                    ownReview.rating = create_review_rating.rating.toInt()
                    ReviewsDatabase.getInstance(this@CreateReviewActivity)!!.reviewsDao().updateReview(ownReview)
                    finish()
                }
                return true
            }
        })

        own_review_rating.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { _, p1, _ ->
                    updateRatingLabel(p1.toInt())
                }
    }

    private fun updateRatingLabel(rating: Int) {
        when (rating) {
            1 -> create_review_rating_label.text = getString(R.string.rating_1_lbl)
            2 -> create_review_rating_label.text = getString(R.string.rating_2_lbl)
            3 -> create_review_rating_label.text = getString(R.string.rating_3_lbl)
            4 -> create_review_rating_label.text = getString(R.string.rating_4_lbl)
            5 -> create_review_rating_label.text = getString(R.string.rating_5_lbl)
        }
    }

    private fun isValidReview(): Boolean {
        if (TextUtils.isEmpty(create_review_own_name.text.toString())) {
            create_review_own_name.error = getString(R.string.required)
            return false
        }
        return true
    }
}