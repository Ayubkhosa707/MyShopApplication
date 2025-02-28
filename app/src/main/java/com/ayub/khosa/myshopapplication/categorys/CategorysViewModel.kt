package com.ayub.khosa.myshopapplication.categorys

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myshopapplication.api.RetrofitBuilder
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import kotlinx.coroutines.launch

class CategorysViewModel : ViewModel() {


    val categorysItems = MutableLiveData<ListCATEGORYS>()
    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()

    @SuppressLint("SuspiciousIndentation")
    fun getAllCategorys() {
        _is_busy.value = true


        viewModelScope.launch {

            try {
                val response = MainActivityRepository(RetrofitBuilder.apiService).getAllCategorys()
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                if (response.response == "Success") {
                    val data = kotlin.collections.ArrayList<CATEGORY>()
                    response.data.categorys.forEach {
                        PrintLogs.printD(" name of category : " + it.name)
                        var category = CATEGORY(
                            it.name,
                            "https://ayubkhosa.com/ecommerce-website-master/" + it.img,
                            it.id
                        )
                        data.add(category)
                    }
                    var listcategorys = ListCATEGORYS(data)
                    categorysItems.postValue(listcategorys)
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


    }

}