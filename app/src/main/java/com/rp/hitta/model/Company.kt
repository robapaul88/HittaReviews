package com.rp.hitta.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Company {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("displayName")
    @Expose
    var displayName: String? = null
    @SerializedName("legalName")
    @Expose
    var legalName: String? = null
    @SerializedName("orgno")
    @Expose
    var orgno: String? = null
    @SerializedName("suffix")
    @Expose
    var suffix: String? = null
    @SerializedName("hideFinancialFigures")
    @Expose
    var hideFinancialFigures: Boolean = false
    @SerializedName("hideAnnualReports")
    @Expose
    var hideAnnualReports: Boolean = false
    @SerializedName("hideAllSensitiveData")
    @Expose
    var hideAllSensitiveData: Boolean = false
    @SerializedName("allowReviews")
    @Expose
    var allowReviews: Boolean = false
    @SerializedName("allowOffers")
    @Expose
    var allowOffers: Boolean = false
}