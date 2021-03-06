package com.rp.hitta.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.rp.hitta.api.HittaSandbox
import com.rp.hitta.init.Initializer
import com.rp.hitta.model.Review
import java.util.concurrent.Executors

/**
 * Created by roba
 */
@Database(entities = arrayOf(Review::class), version = 1, exportSchema = false)
abstract class ReviewsDatabase : RoomDatabase() {
    abstract fun reviewsDao(): ReviewsDao

    companion object {
        private var sInstance: ReviewsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ReviewsDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(context.applicationContext, ReviewsDatabase::class.java, "hitta_reviews.db")
                        .allowMainThreadQueries()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadScheduledExecutor().execute {
                                    for (review in Initializer.getInitialReviews()) {
                                        ReviewsDatabase.getInstance(context).reviewsDao().insertReview(review)
                                        HittaSandbox.persistReview(review)
                                    }
                                }
                            }
                        })
                        .build()
            }
            return sInstance!!
        }
    }
}