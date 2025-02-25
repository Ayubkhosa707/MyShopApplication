package com.ayub.khosa.myshopapplication.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.api.APIResponce
import com.ayub.khosa.myshopapplication.api.APIResponceUser
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingViewModel(private val repository: MainActivityRepository) : ViewModel() {


    var user = MutableLiveData<USER>()
    val errorMessage = MutableLiveData<String>()

    val _is_busy = MutableLiveData<Boolean>()

    init {
        PrintLogs.printD("SettingViewModel init")

    }






    fun onsetting_user_loginClicked() {

        _is_busy.value = true
        PrintLogs.printD("SettingViewModel  onsetting_user_loginClicked")
        PrintLogs.printD("onsetting_user_loginClicked firstname : " + this.user.value?.first_name)
        PrintLogs.printD("onsetting_user_loginClicked lastname : " + this.user.value?.last_name)

        val response = repository.getLoginUser("ayub.khosa@gmail.com", "ayub")
        response.enqueue(object : Callback<APIResponceUser> {
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

                        user.value = it


                    }

                } else {

                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
                _is_busy.value = false
            }

            override fun onFailure(call: Call<APIResponceUser>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
                errorMessage.value = t.message
                _is_busy.value = false
            }

        })

    }

    fun onsetting_user_is_logged_inClicked() {
        _is_busy.value = true


        val response = repository.is_logged_in()
        response.enqueue(object : Callback<APIResponce> {
            override fun onResponse(call: Call<APIResponce>, response: Response<APIResponce>) {
                if (response.body()?.response.equals("Success")) {
                    PrintLogs.printD(" onResponse Success :  " + response.body())

                    PrintLogs.printD(" onResponse Success  data :  " + response.body()?.data)
                    PrintLogs.printD(" onResponse Success  raw :  " + response.raw())
                    response.body()?.data?.let {

                        user.value?.tokenCode = it.toString()

                    }

                } else {
                    PrintLogs.printD(" onResponse .......  .... .....  " + response.body())
                }
                _is_busy.value = false
            }

            override fun onFailure(call: Call<APIResponce>, t: Throwable) {
                PrintLogs.printD("onFailure: ${t.message}")
                errorMessage.value = t.message
                _is_busy.value = false
            }

        })
    }




}


