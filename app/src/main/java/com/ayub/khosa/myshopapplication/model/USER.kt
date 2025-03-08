package com.ayub.khosa.myshopapplication.model

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayub.khosa.myshopapplication.BR
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users")
data class USER(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "first_name") var first_name: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "last_name") var last_name: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "password") var password: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "user_id") var user_id: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "email_id") var email_id: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "tokenCode") var tokenCode: String
) : BaseObservable() {

}