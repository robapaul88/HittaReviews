package com.rp.hitta.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.rp.hitta.model.Review

/**
 * Created by roba
 */
interface ReviewsDao {
    @Query("SELECT * FROM review")
    fun getAllReviews(): LiveData<List<Review>>

    @Insert
    fun insertReview(review: Review)
}