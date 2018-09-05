package com.rp.hitta.api

import com.rp.hitta.model.CompaniesSearchResult

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by roba
 */
interface CompaniesSearchClient {
    @GET("/search/v7/app/company/ctyfiintu")
    fun searchCompanies(): Call<CompaniesSearchResult>
}