package com.rp.hitta.api

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.rp.hitta.db.ReviewsDao
import com.rp.hitta.db.ReviewsDatabase
import com.rp.hitta.model.Review

class ReviewsViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewsDao: ReviewsDao? = ReviewsDatabase.getInstance(application).reviewsDao()
    private val reviewsList: LiveData<List<Review>>

    init {
        reviewsList = reviewsDao!!.getAllReviews()
    }

    fun getReviewsList(): LiveData<List<Review>> {
        return reviewsList
    }

    fun getOwnReview(): Review? {
        return reviewsDao!!.getOwnReview()
    }
}