package com.ayub.khosa.myshopapplication.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.api.ApiClient
import com.ayub.khosa.myshopapplication.api.APIResponce
import com.ayub.khosa.myshopapplication.model.ResponceUser
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class SettingViewModel : ViewModel() {


    var user: MutableLiveData<USER> = MutableLiveData<USER>()
    // var retrofitClient: RetrofitClient = RetrofitClient()


    init {
        PrintLogs.printD("SettingViewModel init")
        var temp = USER("firstname", "lastname", "email_id", "password", 0)
        user = MutableLiveData<USER>(temp)
        PrintLogs.printD("SettingViewModel init" + user.value?.first_name)
        PrintLogs.printD("SettingViewModel init" + user.value?.last_name)

    }

    fun setUser(user: USER) {

        this.user.value?.first_name = user.first_name
        this.user.value?.last_name = user.last_name
        PrintLogs.printD("setUser firstname" + this.user.value?.first_name)
        PrintLogs.printD("setUser lastname" + this.user.value?.last_name)
    }

    fun viewmodelUserFirstname(): String {
        return this.user.value?.first_name.toString()

    }

    fun viewmodelUserLastname(): String {
        return this.user.value?.last_name.toString()
    }


    fun onsetting_user_loginClicked() {
        PrintLogs.printD("SettingViewModel  onsetting_user_loginClicked")
        PrintLogs.printD("onsetting_user_loginClicked firstname : " + this.user.value?.first_name)
        PrintLogs.printD("onsetting_user_loginClicked lastname : " + this.user.value?.last_name)

        getLoginUser()

    }

    fun onsetting_user_is_logged_inClicked() {

        is_logged_in()
    }


    private fun is_logged_in() {

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

                        // setUser(it)
                        user.value?.last_name = it.toString()
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

    private fun getLoginUser() {

//        val call: Call<ResponceUser> =
//            retrofitClient.getLoginUser("ayub.khosa@gmail.com", "ayub")

        val call = ApiClient.apiService.getLogin("btn-login", "ayub.khosa@gmail.com", "ayub")
        call.enqueue(object : Callback<ResponceUser> {
            override fun onResponse(
                call: Call<ResponceUser>,
                response: Response<ResponceUser>
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
                        user.value?.first_name = "email :" + it.email_id + " id " + it.user_id
                        user.value?.last_name =
                            "first_name :" + it.first_name + " last_name " + it.last_name
                    }

                } else {

                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
            }

            override fun onFailure(call: Call<ResponceUser>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
            }
        })
        PrintLogs.printD("**************************************************************")

    }
}