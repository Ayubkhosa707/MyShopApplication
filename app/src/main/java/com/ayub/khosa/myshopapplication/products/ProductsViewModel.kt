package com.ayub.khosa.myshopapplication.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import kotlinx.coroutines.launch

class ProductsViewModel(repository: MainActivityRepository) : ViewModel() {
    val repository = repository
    val productsItems = MutableLiveData<ListPRODUCTS>()

    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()


    fun getAllProducts() {
        _is_busy.value = true
        PrintLogs.printD(" -----------  getAllProducts  -------------")
        viewModelScope.launch {

            try {

                val response = repository.getAllProducts()
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)

                if (response.response == "Success") {
                    val data = kotlin.collections.ArrayList<PRODUCT>()
                    response.data.products.forEach {
                        PrintLogs.printD(" name of product : " + it.name)
                        PrintLogs.printD(" product_id of product : " + it.product_id)
                        PrintLogs.printD(" category of product : " + it.category)
                        PrintLogs.printD(" img of product : " + it.img)
                        PrintLogs.printD(" price of product : " + it.price)

                        data.add(it)
                        addProductinDB(it)
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
            PrintLogs.printD(" -----------  getAllProducts  END -------------")
        }


    }

    fun getAllProductsDB() {
        _is_busy.value = true
        PrintLogs.printD(" -----------  getAllProductsDB   -------------")
        viewModelScope.launch {

            try {
                val response = repository.getProducts_DB()

                productsItems.postValue(response)
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
            _is_busy.value = false
        }
        PrintLogs.printD(" -----------  getAllProductsDB END  -------------")

    }

    fun addProductinDB(product: PRODUCT) {
        _is_busy.value = true
        try {
            if (repository.fetchProductByName(product.name, product.product_id) != null) {
                repository.updateProductinDB(product)
            } else {
                repository.insertProductinDB(product)
            }
            _is_busy.value = false
        } catch (e: Exception) {
            errorMessage.postValue(e.message)
            _is_busy.value = false
            PrintLogs.printD("Exception: ${e.message}")
        }
    }
}




