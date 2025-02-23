package com.ayub.khosa.myshopapplication.api

import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.google.gson.annotations.SerializedName

data class APIResponceListPRODUCTS(
    @SerializedName("data")
    val `data`: ListPRODUCTS,
    @SerializedName("response")
    val response: String
)
