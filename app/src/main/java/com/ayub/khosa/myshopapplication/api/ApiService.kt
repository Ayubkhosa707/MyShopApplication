package com.ayub.khosa.myshopapplication.api

import com.ayub.khosa.myshopapplication.model.APIResponce
import com.ayub.khosa.myshopapplication.model.APIResponceListCATEGORYS
import com.ayub.khosa.myshopapplication.model.APIResponceListPRODUCTS
import com.ayub.khosa.myshopapplication.model.APIResponceUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun getLogin(
        @Field("btn-login") btn_login: String,
        @Field("lemail") email: String,
        @Field("lpassword") password: String
    ): APIResponceUser

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun is_logged_in(
        @Field("is_logged_in") is_logged_in: String
    ): APIResponce

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun get_ListPRODUCTS(
        @Field("ListPRODUCTS") listPRODUCTS: String
    ): APIResponceListPRODUCTS

    @FormUrlEncoded
    @POST("ecommerce-website-master/authmobile.php")
    suspend fun get_ListCATEGORYS(
        @Field("ListCATEGORYS") listCATEGORYS: String
    ): APIResponceListCATEGORYS

}

