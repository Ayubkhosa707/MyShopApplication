package com.ayub.khosa.myshopapplication.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myshopapplication.api.RetrofitBuilder
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import kotlinx.coroutines.launch


class SettingViewModel : ViewModel() {


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


        viewModelScope.launch {

            try {
                val response = MainActivityRepository(RetrofitBuilder.apiService).getLoginUser(
                    "ayub.khosa@gmail.com",
                    "ayub"
                )

                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                if (response.response == "Success") {

                    PrintLogs.printD(" onResponse Success data email :  " + response.data.email_id)
                    PrintLogs.printD(" onResponse Success data first_name :  " + response.data.first_name)
                    PrintLogs.printD(" onResponse Success data last_name :  " + response.data.last_name)
                    PrintLogs.printD(" onResponse Success data user_id :  " + response.data.user_id)
                    PrintLogs.printD(" onResponse Success data password :  " + response.data.password)


                    user.postValue(response.data)


                } else {
                    errorMessage.value = " Error getLoginUser  "
                }

                _is_busy.value = false
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
        }

    }

    fun onsetting_user_is_logged_inClicked() {
        _is_busy.value = true

        viewModelScope.launch {

            try {
                val response = MainActivityRepository(RetrofitBuilder.apiService).is_logged_in()
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                if (response.response == "Success") {
                    PrintLogs.printD(" onResponse Success  data :  " + response.data)

                    user.value?.tokenCode = response.data


                } else {
                    errorMessage.value = " Error onsetting_user_is_logged_inClicked  "
                }
                _is_busy.value = false

            } catch (e: Exception) {
                PrintLogs.printD("onFailure: ${e.message}")
                errorMessage.value = e.message
                _is_busy.value = false
            }
        }
    }


}


