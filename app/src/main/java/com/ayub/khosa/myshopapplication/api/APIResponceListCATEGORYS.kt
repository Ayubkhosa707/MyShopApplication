package com.ayub.khosa.myshopapplication.api

import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.google.gson.annotations.SerializedName

data class APIResponceListCATEGORYS(
    @SerializedName("data")
    val `data`: ListCATEGORYS,
    @SerializedName("response")
    val response: String
)