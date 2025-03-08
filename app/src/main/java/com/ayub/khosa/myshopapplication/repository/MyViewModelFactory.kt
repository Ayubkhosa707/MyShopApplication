package com.ayub.khosa.myshopapplication.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayub.khosa.myshopapplication.categorys.CategorysViewModel
import com.ayub.khosa.myshopapplication.products.ProductsViewModel
import com.ayub.khosa.myshopapplication.setting.SettingViewModel

class MyViewModelFactory(repository: MainActivityRepository) :
    ViewModelProvider.Factory {
    val repository = repository
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            ProductsViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CategorysViewModel::class.java)) {
            CategorysViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            SettingViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}