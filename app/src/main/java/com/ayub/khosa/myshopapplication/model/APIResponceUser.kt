package com.ayub.khosa.myshopapplication.model

import com.google.gson.annotations.SerializedName

data class APIResponceUser(
    @SerializedName("data")
    val `data`: USER,
    @SerializedName("response")
    val response: String
)
