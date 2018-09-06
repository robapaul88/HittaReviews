package com.rp.hitta.api

import android.util.Log
import com.rp.hitta.model.Review
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by roba
 */
class HittaSandbox {
    companion object {
        fun persistReview(review: Review) {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://test.hitta.se")
                    .addConverterFactory(GsonConverterFactory.create()).build()

            val client = retrofit.create<HittaSandboxAPI>(HittaSandboxAPI::class.java)
            client.persistReview(review.rating, review.content!!, review.userName!!, "ctyfiintu")
                    .enqueue(object : Callback<Review> {
                        override fun onFailure(call: Call<Review>?, t: Throwable?) {
                            Log.d("HittaSandbox", "persistReview onFailure$t")
                        }

                        override fun onResponse(call: Call<Review>?, response: Response<Review>?) {
                            Log.d("HittaSandbox", "persistReview onResponse$response")
                        }
                    })

        }
    }
}
