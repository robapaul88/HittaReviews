package com.rp.hitta.api

import com.rp.hitta.model.Review
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by roba
 */
interface HittaSandboxAPI {
    @Headers(
            "Content-Type: application/x-www-form-urlencoded",
            "Charset: UTF-8",
            "X-HITTA-DEVICE-NAME: MOBILE_WEB",
            "X-HITTA-SHARED-IDENTIFIER: 15188693697264027"
    )
    @POST("/reviews/v1/company")
    @FormUrlEncoded
    fun persistReview(@Field("score") score: Int,
                      @Field("comment") comment: String,
                      @Field("userName") userName: String,
                      @Field("companyId") companyId: String): Call<Review>
}