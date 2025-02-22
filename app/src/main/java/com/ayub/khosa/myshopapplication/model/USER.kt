package com.ayub.khosa.myshopapplication.model

import androidx.databinding.BaseObservable
import com.ayub.khosa.myshopapplication.BR
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class USER() : BaseObservable(), Serializable {


    constructor(
        firstname: String,
        lastname: String,
        email_id: String,
        password: String,
        user_id: Int
    ) : this() {

        this.first_name = firstname
        this.last_name = lastname
        this.email_id = email_id
        this.password = password
        this.userStatus = UserStatusType.NO
        this.tokenCode = "XXXXX"
        this.user_id = user_id
    }

    @androidx.databinding.Bindable
    @SerializedName("first_name")
    var first_name: String = "USER first_name"
        set(first_name: String) {
            field = first_name
            PrintLogs.printD("user set firstname" + first_name)
            notifyPropertyChanged(BR.first_name)
        }

    @androidx.databinding.Bindable
    @SerializedName("last_name")
    var last_name: String = "USER last_name"
        set(last_name: String) {
            field = last_name
            PrintLogs.printD("user set lastname" + last_name)
            notifyPropertyChanged(BR.last_name)
        }


    @androidx.databinding.Bindable
    @SerializedName("email_id")
    var email_id: String = "USER email"
        set(email_id: String) {
            field = email_id
            PrintLogs.printD("user set email_id  ----- " + email_id)
            notifyPropertyChanged(BR.email_id)
        }

    @androidx.databinding.Bindable
    @SerializedName("password")
    var password: String = "USER password"
        set(password: String) {
            field = password
            PrintLogs.printD("user set password ---- " + password)
            notifyPropertyChanged(BR.password)
        }

    @androidx.databinding.Bindable
    @SerializedName("userStatus")
    var userStatus: UserStatusType = UserStatusType.NO
        get() {
            return this.userStatus
        }
        set(userStatus) {
            field = userStatus
            PrintLogs.printD("user set userStatus -- " + userStatus)
            notifyPropertyChanged(BR.userStatus)
        }

    @androidx.databinding.Bindable
    @SerializedName("tokenCode")
    var tokenCode: String = "USER tokenCode XXXXX"
        set(tokenCode: String) {
            field = tokenCode
            PrintLogs.printD("user set tokenCode --" + tokenCode)
            notifyPropertyChanged(BR.tokenCode)
        }

    @androidx.databinding.Bindable
    var user_id: Int = 0
        set(user_id: Int) {
            field = user_id
            PrintLogs.printD("user set user_id --- " + user_id)
            notifyPropertyChanged(BR.user_id)
        }
}

