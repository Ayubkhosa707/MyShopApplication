package com.ayub.khosa.myshopapplication.repository

import com.ayub.khosa.myshopapplication.api.APIResponce
import com.ayub.khosa.myshopapplication.api.APIResponceUser
import com.ayub.khosa.myshopapplication.api.ApiService


class MainActivityRepository(private val apiService: ApiService) {

    suspend fun getAllProducts() = apiService.get_ListPRODUCTS("ListPRODUCTS")

    suspend fun getAllCategorys() = apiService.get_ListCATEGORYS("ListCATEGORYS")

    suspend fun getLoginUser(email: String, password: String): APIResponceUser =
        apiService.getLogin("btn-login", email, password)

    suspend fun is_logged_in(): APIResponce = apiService.is_logged_in("is_logged_in")


}