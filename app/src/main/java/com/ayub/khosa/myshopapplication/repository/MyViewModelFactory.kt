package com.ayub.khosa.myshopapplication.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayub.khosa.myshopapplication.categorys.CategorysViewModel
import com.ayub.khosa.myshopapplication.products.ProductsViewModel
import com.ayub.khosa.myshopapplication.setting.SettingViewModel

class MyViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            ProductsViewModel() as T
        } else if (modelClass.isAssignableFrom(CategorysViewModel::class.java)) {
            CategorysViewModel() as T
        } else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            SettingViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}