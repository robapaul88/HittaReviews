package com.rp.hitta.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.rp.hitta.model.Review
import com.rp.hitta.mock.MockData
import java.util.concurrent.Executors

/**
 * Created by roba
 */
@Database(entities = [Review::class], version = 1, exportSchema = false)
abstract class ReviewsDatabase : RoomDatabase() {
    abstract fun reviewsDao(): ReviewsDao

    companion object : SingletonHolder<ReviewsDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, ReviewsDatabase::class.java, "hitta_reviews.db")
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadScheduledExecutor().execute {
                            for (review in MockData.getMockReviews()) {
                                ReviewsDatabase.getInstance(it).reviewsDao().insertReview(review)
                            }
                        }
                    }
                })
                .build()
    })
}