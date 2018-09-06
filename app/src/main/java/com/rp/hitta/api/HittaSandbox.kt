package com.rp.hitta.api

import com.rp.hitta.model.Review
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by roba
 */
class HittaSandbox {
    companion object {
        fun persistReview(review: Review) {
            val builder = Retrofit.Builder()
                    .baseUrl("https://test.hitta.se")
                    .addConverterFactory(GsonConverterFactory.create())

            val retrofit = builder.build()

            val client = retrofit.create<HittaSandboxAPI>(HittaSandboxAPI::class.java)
            val call = client.persistReview(review.rating, review.content!!, review.userName!!, "ctyfiintu")

        }
    }
}
