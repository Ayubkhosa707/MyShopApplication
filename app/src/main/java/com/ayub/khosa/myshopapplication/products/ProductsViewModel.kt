package com.ayub.khosa.myshopapplication.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.api.APIResponceListPRODUCTS
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel(private val repository: MainActivityRepository) : ViewModel() {

    val productsItems = MutableLiveData<ListPRODUCTS>()
    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()


    fun getAllProducts() {
        _is_busy.value = true
        val response = repository.getAllProducts()
        response.enqueue(object : Callback<APIResponceListPRODUCTS> {
            override fun onResponse(
                call: Call<APIResponceListPRODUCTS>,
                response: Response<APIResponceListPRODUCTS>
            ) {
                if (response.body()?.response.equals("Success")) {
                    PrintLogs.printD(" onResponse Success :  " + response.body())
                    PrintLogs.printD(" onResponse Success  data :  " + response.body()?.data)


                    response.body()?.data?.let {

                        val data = kotlin.collections.ArrayList<PRODUCT>()

                        it.products.forEach {
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
                        productsItems.value = listproduct
                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                    errorMessage.value = "   " + response.body().toString()
                }
                _is_busy.value = false
            }

            override fun onFailure(call: Call<APIResponceListPRODUCTS>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
                errorMessage.value = t.message
                _is_busy.value = false
            }

        })

    }
}




