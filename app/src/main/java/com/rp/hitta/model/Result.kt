package com.rp.hitta.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("companies")
    @Expose
    var companies: Companies? = null
}