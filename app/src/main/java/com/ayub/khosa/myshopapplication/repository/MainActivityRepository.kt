package com.ayub.khosa.myshopapplication.repository

import androidx.lifecycle.MutableLiveData
import com.ayub.khosa.myshopapplication.api.APIResponce
import com.ayub.khosa.myshopapplication.api.APIResponceListCATEGORYS
import com.ayub.khosa.myshopapplication.api.APIResponceListPRODUCTS
import com.ayub.khosa.myshopapplication.api.APIResponceUser
import com.ayub.khosa.myshopapplication.api.ApiClient
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MainActivityRepository {
    private var listPRODUCTSData: MutableLiveData<ListPRODUCTS> = MutableLiveData<ListPRODUCTS>()

    private var listCATEGORYSData: MutableLiveData<ListCATEGORYS> = MutableLiveData<ListCATEGORYS>()
    private var main_user: MutableLiveData<USER> = MutableLiveData<USER>()

    fun getlistPRODUCTApiCall(): MutableLiveData<ListPRODUCTS> {
        PrintLogs.printD("MainActivityRepository  getlistPRODUCTApiCall")
        val call = ApiClient.apiService.get_ListPRODUCTS("ListPRODUCTS")
        call.enqueue(object : Callback<APIResponceListPRODUCTS> {
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
                        listPRODUCTSData = MutableLiveData<ListPRODUCTS>(listproduct)
                        PrintLogs.printD(" listPRODUCTSData.value.products.size ----  " + listPRODUCTSData.value?.products?.size)

                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
            }

            override fun onFailure(call: Call<APIResponceListPRODUCTS>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
            }
        })




        PrintLogs.printD("MainActivityRepository  getlistPRODUCTApiCall")
        return listPRODUCTSData
    }

    fun getlistCATEGORYApiCall(): MutableLiveData<ListCATEGORYS> {
        PrintLogs.printD("***** getlistCATEGORYApiCall Start ****")
        PrintLogs.printD("MainActivityRepository  getlistCATEGORYApiCall")

        val call = ApiClient.apiService.get_ListCATEGORYS("ListCATEGORYS")

        call.enqueue(object : Callback<APIResponceListCATEGORYS> {
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

                        listCATEGORYSData = MutableLiveData<ListCATEGORYS>(listcategorys)


                        PrintLogs.printD(" listCATEGORYSData.value.categorys.size ---->  " + listCATEGORYSData.value?.categorys?.size)

                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
            }

            override fun onFailure(call: Call<APIResponceListCATEGORYS>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
            }
        })

        PrintLogs.printD("***** getlistCATEGORYApiCall End ****  " + listCATEGORYSData.value?.categorys?.size)
        return listCATEGORYSData
    }

    fun getUserApiCall(): MutableLiveData<USER> {

        var temp = USER("firstname", "lastname", "email_id", "password", 0)
        main_user = MutableLiveData<USER>(temp)
        return main_user
    }


    fun getLoginUser() {

//        val call: Call<ResponceUser> =
//            retrofitClient.getLoginUser("ayub.khosa@gmail.com", "ayub")

        val call = ApiClient.apiService.getLogin("btn-login", "ayub.khosa@gmail.com", "ayub")
        call.enqueue(object : Callback<APIResponceUser> {
            override fun onResponse(
                call: Call<APIResponceUser>,
                response: Response<APIResponceUser>
            ) {
                if (response.body()?.response.equals("Success")) {
                    PrintLogs.printD(" onResponse Success :  " + response.body())
                    PrintLogs.printD(" onResponse Success data email :  " + response.body()?.data?.email_id)
                    PrintLogs.printD(" onResponse Success data first_name :  " + response.body()?.data?.first_name)
                    PrintLogs.printD(" onResponse Success data last_name :  " + response.body()?.data?.last_name)
                    PrintLogs.printD(" onResponse Success data user_id :  " + response.body()?.data?.user_id)
                    PrintLogs.printD(" onResponse Success data password :  " + response.body()?.data?.password)

                    response.body()?.data?.let {

                        // setUser(it)
                        main_user.value?.first_name = "email :" + it.email_id + " id " + it.user_id
                        main_user.value?.last_name =
                            "first_name :" + it.first_name + " last_name " + it.last_name
                    }

                } else {

                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
            }

            override fun onFailure(call: Call<APIResponceUser>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
            }
        })
        PrintLogs.printD("**************************************************************")

    }

    fun is_logged_in() {

        // var retrofitClient: RetrofitClient = RetrofitClient()
//        val call: Call<RetrofitResponce> =
//            retrofitClient.is_logged_in()


        val call = ApiClient.apiService.is_logged_in("is_logged_in")
        call.enqueue(object : Callback<APIResponce> {
            override fun onResponse(
                call: Call<APIResponce>,
                response: Response<APIResponce>
            ) {
                if (response.body()?.response.equals("Success")) {
                    PrintLogs.printD(" onResponse Success :  " + response.body())

                    PrintLogs.printD(" onResponse Success  data :  " + response.body()?.data)
                    PrintLogs.printD(" onResponse Success  raw :  " + response.raw())
                    response.body()?.data?.let {


                        main_user.value?.last_name = it.toString()
                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
            }

            override fun onFailure(call: Call<APIResponce>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
            }
        })
        PrintLogs.printD("------------------------------------------------------------------")

    }


}