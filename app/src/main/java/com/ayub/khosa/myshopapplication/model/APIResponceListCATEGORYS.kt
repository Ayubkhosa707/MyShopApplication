package com.ayub.khosa.myshopapplication.model

import com.google.gson.annotations.SerializedName

data class APIResponceListCATEGORYS(
    @SerializedName("data")
    val `data`: ListCATEGORYS,
    @SerializedName("response")
    val response: String
)