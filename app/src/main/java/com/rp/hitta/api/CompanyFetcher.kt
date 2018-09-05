package com.rp.hitta.api

import android.text.TextUtils
import com.rp.hitta.model.CompaniesSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by roba
 */
class CompanyFetcher {
    companion object {
        fun fetchAsync(onDoneListener: OnDoneListener<String>) {
            val builder = Retrofit.Builder()
                    .baseUrl("https://api.hitta.se")
                    .addConverterFactory(GsonConverterFactory.create())

            val retrofit = builder.build()

            val client = retrofit.create<CompaniesSearchClient>(CompaniesSearchClient::class.java)
            val call = client.searchCompanies()

            call.enqueue(object : Callback<CompaniesSearchResult> {
                override fun onFailure(call: Call<CompaniesSearchResult>, t: Throwable) {
                    onDoneListener.onFail()
                }

                override fun onResponse(call: Call<CompaniesSearchResult>, response: Response<CompaniesSearchResult>) {
                    if (response.body() == null) {
                        onDoneListener.onFail()
                    }
                    val displayName = response.body()?.result?.companies?.company?.get(0)?.displayName
                    if (TextUtils.isEmpty(displayName)) {
                        onDoneListener.onFail()
                    } else {
                        onDoneListener.onSuccess(displayName!!)
                    }
                }
            })
        }
    }
}
