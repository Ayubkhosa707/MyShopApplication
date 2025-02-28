package com.ayub.khosa.myshopapplication.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myshopapplication.api.RetrofitBuilder
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    val productsItems = MutableLiveData<ListPRODUCTS>()
    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()


    fun getAllProducts() {
        _is_busy.value = true

        viewModelScope.launch {

            try {

                val response = MainActivityRepository(RetrofitBuilder.apiService).getAllProducts()
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                if (response.response == "Success") {
                    val data = kotlin.collections.ArrayList<PRODUCT>()
                    response.data.products.forEach {
                        PrintLogs.printD(" name of product : " + it.name)


                        var product =
                            PRODUCT(
                                it.name,
                                "https://ayubkhosa.com/ecommerce-website-master/" + it.img,
                                it.category,
                                it.description,
                                it.price,
                                it.id
                            )

                        data.add(product)
                    }

                    PrintLogs.printD(" data.size ----  " + data.size)
                    var listproduct: ListPRODUCTS = ListPRODUCTS(data)
                    PrintLogs.printD(" listproduct.products.size ----  " + listproduct.products.size)

                    productsItems.postValue(listproduct)
                } else {
                    errorMessage.value = " Error loadinng products  "
                }
                _is_busy.value = false

            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }

            _is_busy.value = false

        }


    }
}




