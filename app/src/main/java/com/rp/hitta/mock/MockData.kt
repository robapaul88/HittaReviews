package com.rp.hitta.mock

import com.rp.hitta.model.Review

/**
 * Created by roba
 */
class MockData {
    companion object {
        fun getMockReviews(): List<Review> {
            val reviews = ArrayList<Review>()
            val review1 = Review("", "", System.currentTimeMillis(), 3, "hitta.se", "test")
            reviews.add(review1)
            val review2 = Review("", "", System.currentTimeMillis(), 3, "hitta.se", "test")
            reviews.add(review2)
            val review3 = Review("", "", System.currentTimeMillis(), 3, "hitta.se", "test")
            reviews.add(review3)
            return reviews
        }
    }
}