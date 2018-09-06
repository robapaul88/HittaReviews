package com.rp.hitta

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rp.hitta.api.ReviewsViewModel
import com.rp.hitta.model.Review
import com.rp.hitta.ui.ReviewsAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val companyName = intent.getStringExtra(KEY_COMPANY_NAME)
        companyName?.let { company_name.text = it }

        val adapter = ReviewsAdapter()
        reviewsRecyclerView.adapter = adapter

        reviewsViewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
        reviewsViewModel.getReviewsList().observe(this, Observer<List<Review>> {
            adapter.updateReviews(it)
        })
    }
}