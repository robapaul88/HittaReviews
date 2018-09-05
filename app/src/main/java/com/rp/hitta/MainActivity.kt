package com.rp.hitta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rp.hitta.api.ReviewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.Nullable
import com.rp.hitta.model.Review
import java.util.*


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

    private val reviewsViewModel: ReviewsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val companyName = intent.getStringExtra(KEY_COMPANY_NAME)
        companyName?.let { company_name.text = it }

        reviewsViewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
        initAdapter()
        reviewsViewModel.getReviewsList().observe(this, object : Observer<List<Review>>() {
            fun onChanged(@Nullable directors: List<Review>) {
                //TODO refresh adapter
            }
        })
        initSwipeToRefresh()
    }

    private fun initAdapter() {
    }

    private fun initSwipeToRefresh() {
    }
}
