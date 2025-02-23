package com.ayub.khosa.myshopapplication.api

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
    ): Call<APIResponceUser>

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    fun is_logged_in(
        @Field("is_logged_in") is_logged_in: String
    ): Call<APIResponce>

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    fun get_ListPRODUCTS(
        @Field("ListPRODUCTS") listPRODUCTS: String
    ): Call<APIResponceListPRODUCTS>

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    fun get_ListCATEGORYS(
        @Field("ListCATEGORYS") listCATEGORYS: String
    ): Call<APIResponceListCATEGORYS>

}