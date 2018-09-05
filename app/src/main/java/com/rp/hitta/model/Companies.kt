package com.rp.hitta.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Companies {
    @SerializedName("company")
    @Expose
    var company: List<Company>? = null
}