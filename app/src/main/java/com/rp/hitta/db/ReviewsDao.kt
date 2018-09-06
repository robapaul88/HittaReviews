package com.rp.hitta.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.rp.hitta.model.Review


/**
 * Created by roba
 */
@Dao
interface ReviewsDao {
    @Query("SELECT * FROM review WHERE isCurrentUser == 0 ORDER BY created_at DESC")
    fun getAllReviews(): LiveData<List<Review>>

    @Query("SELECT * FROM review WHERE isCurrentUser == 1")
    fun getOwnReview(): Review?

    @Update(onConflict = REPLACE)
    fun updateReview(review: Review)

    @Insert
    fun insertReview(review: Review)
}