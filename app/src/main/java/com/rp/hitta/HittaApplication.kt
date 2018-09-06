package com.rp.hitta

import android.app.Application
import com.rp.hitta.db.ReviewsDatabase

/**
 * Created by roba
 */
class HittaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ReviewsDatabase.getInstance(this)
    }
}