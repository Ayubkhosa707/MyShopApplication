package com.ayub.khosa.myshopapplication.api

import com.ayub.khosa.myshopapplication.model.ResponceUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    fun getLogin(
        @Field("btn-login") btn_login: String,
        @Field("lemail") email: String,
        @Field("lpassword") password: String
    ): Call<ResponceUser>

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    fun is_logged_in(
        @Field("is_logged_in") is_logged_in: String
    ): Call<APIResponce>

}