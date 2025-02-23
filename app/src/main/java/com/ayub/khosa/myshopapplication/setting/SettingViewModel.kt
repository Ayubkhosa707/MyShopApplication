package com.ayub.khosa.myshopapplication.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class SettingViewModel : ViewModel() {


    var user: MutableLiveData<USER> = MainActivityRepository.getUserApiCall()


    init {
        PrintLogs.printD("SettingViewModel init")
        PrintLogs.printD("SettingViewModel init" + user.value?.first_name)
        PrintLogs.printD("SettingViewModel init" + user.value?.last_name)

    }

    fun setUser(user: USER) {

        this.user.value?.first_name = user.first_name
        this.user.value?.last_name = user.last_name
        PrintLogs.printD("setUser firstname  " + this.user.value?.first_name)
        PrintLogs.printD("setUser lastname  " + this.user.value?.last_name)
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


        MainActivityRepository.getLoginUser()


    }

    fun onsetting_user_is_logged_inClicked() {

       // is_logged_in()

        MainActivityRepository.is_logged_in()
    }




}