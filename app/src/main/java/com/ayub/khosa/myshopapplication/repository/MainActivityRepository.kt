package com.ayub.khosa.myshopapplication.repository

import com.ayub.khosa.myshopapplication.api.APIResponce
import com.ayub.khosa.myshopapplication.api.APIResponceListCATEGORYS
import com.ayub.khosa.myshopapplication.api.APIResponceListPRODUCTS
import com.ayub.khosa.myshopapplication.api.APIResponceUser
import com.ayub.khosa.myshopapplication.api.ApiService
import retrofit2.Call


class MainActivityRepository(private val apiService: ApiService) {

    fun getAllProducts():
            Call<APIResponceListPRODUCTS> = apiService.get_ListPRODUCTS("ListPRODUCTS")

    fun getAllCategorys():
            Call<APIResponceListCATEGORYS> = apiService.get_ListCATEGORYS("ListCATEGORYS")

    fun getLoginUser(email: String, password: String): Call<APIResponceUser> =
        apiService.getLogin("btn-login", email, password)

    fun is_logged_in(): Call<APIResponce> = apiService.is_logged_in("is_logged_in")

}