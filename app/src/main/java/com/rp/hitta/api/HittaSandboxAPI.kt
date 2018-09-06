package com.rp.hitta.api

import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Created by roba
 */
interface HittaSandboxAPI {
    @POST("/reviews/v1/company")
    fun persistReview(@Field("score") score: Int,
                      @Field("comment") comment: String,
                      @Field("userName") userName: String,
                      @Field("companyId") companyId: String)
}