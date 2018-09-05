package com.rp.hitta.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rp.hitta.model.Result

class CompaniesSearchResult {
    @SerializedName("result")
    @Expose
    var result: Result? = null
}