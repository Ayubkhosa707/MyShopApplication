package com.ayub.khosa.myshopapplication.model

import com.google.gson.annotations.SerializedName

data class ResponceUser(
    @SerializedName("data")
    val `data`: USER,
    @SerializedName("response")
    val response: String
)
