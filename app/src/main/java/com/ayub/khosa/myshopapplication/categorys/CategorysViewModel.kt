package com.ayub.khosa.myshopapplication.categorys

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.api.APIResponceListCATEGORYS
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import retrofit2.Call
import retrofit2.Response

class CategorysViewModel(private val repository: MainActivityRepository) : ViewModel() {


    val categorysItems = MutableLiveData<ListCATEGORYS>()
    val errorMessage = MutableLiveData<String>()
    val _is_busy = MutableLiveData<Boolean>()

    fun getAllCategorys() {
        _is_busy.value = true
        val response = repository.getAllCategorys()
        response.enqueue(object : retrofit2.Callback<APIResponceListCATEGORYS> {
            override fun onResponse(
                call: Call<APIResponceListCATEGORYS>,
                response: Response<APIResponceListCATEGORYS>
            ) {
                if (response.body()?.response.equals("Success")) {
                    PrintLogs.printD(" onResponse Success :  " + response.body())
                    PrintLogs.printD(" onResponse Success  data :  " + response.body()?.data)


                    response.body()?.data?.let {
                        val data = kotlin.collections.ArrayList<CATEGORY>()

                        it.categorys.forEach {

                            PrintLogs.printD(" name of category : " + it.name)


                            var category = CATEGORY(
                                it.name,
                                "https://ayubkhosa.com/ecommerce-website-master/" + it.img,
                                it.id
                            )

                            data.add(category)
                        }

                        PrintLogs.printD(" data.size ----  " + data.size)
                        var listcategorys = ListCATEGORYS(data)
                        PrintLogs.printD(" listcategorys.categorys.size ----  " + listcategorys.categorys.size)

                        categorysItems.value = listcategorys

                        PrintLogs.printD(" categorysItems.value?.categorys?.size ----  " + categorysItems.value?.categorys?.size)


                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())

                    errorMessage.value = "   " + response.body().toString()
                }
                _is_busy.value = false

            }

            override fun onFailure(call: Call<APIResponceListCATEGORYS>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
                errorMessage.value = t.message
                _is_busy.value = false
            }

        })



    }

}