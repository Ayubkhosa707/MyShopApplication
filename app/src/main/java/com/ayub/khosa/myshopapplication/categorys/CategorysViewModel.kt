package com.ayub.khosa.myshopapplication.categorys

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myshopapplication.model.APIResponceListCATEGORYS
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import kotlinx.coroutines.launch

class CategorysViewModel(repository: MainActivityRepository) : ViewModel() {

    val repository = repository
    val categorysItems = MutableLiveData<ListCATEGORYS>()
    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()

    fun getAllCategorys() {
        _is_busy.value = true

        PrintLogs.printD(" --------  getAllCategorys  -----------")
        viewModelScope.launch {

            try {
                val response: APIResponceListCATEGORYS = repository.getAllCategorys()
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                if (response.response == "Success") {
                    val data = kotlin.collections.ArrayList<CATEGORY>()
                    response.data.categorys.forEach {
                        PrintLogs.printD(" name of category : " + it.name)
                        PrintLogs.printD(" category_id of category : " + it.category_id)
                        PrintLogs.printD(" img of category : " + it.img)
                        PrintLogs.printD(" category_id of category : " + it.category_id)

                        data.add(it)
                        addCategoryinDB(it)
                    }
                    categorysItems.value = response.data
                } else {
                    errorMessage.value = " Error loadinng categorys  "
                }
                _is_busy.value = false

            } catch (e: Exception) {
                errorMessage.value = e.message
                _is_busy.value = false
                PrintLogs.printD("Exception  " + e.message)
            }


        }

        PrintLogs.printD(" --------  getAllCategorys   END  -----------")
    }

    private fun addCategoryinDB(category: CATEGORY) {

        _is_busy.value = true
        try {
            if (repository.fetchCategoryByName(category.name, category.category_id) != null) {
                repository.updateCategoryinDB(category)
            } else {
                repository.insertCategoryinDB(category)
            }
            _is_busy.value = false
        } catch (e: Exception) {
            errorMessage.postValue(e.message)
            _is_busy.value = false
            PrintLogs.printD("Exception: ${e.message}")
        }
    }


    fun getAllCategoryDB() {
        _is_busy.value = true

        viewModelScope.launch {

            try {
                val response: ListCATEGORYS = repository.getCategrys_DB()
                PrintLogs.printD(" -----------getAllCategoryDB ----------")
                response.categorys.forEach {
                    PrintLogs.printD(" name of category : " + it.name)
                    PrintLogs.printD(" id of category : " + it.id)
                    PrintLogs.printD(" img of category : " + it.img)
                    PrintLogs.printD(" category_id of category : " + it.category_id)

                }


                categorysItems.value = response
                PrintLogs.printD("getAllCategoryDB categorysItems --  " + categorysItems.value?.categorys?.size)
                PrintLogs.printD(" -----------getAllCategoryDB   END --------")
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
            _is_busy.value = false
        }
    }

}