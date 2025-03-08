package com.ayub.khosa.myshopapplication.model

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class PRODUCT(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "product_id") var product_id: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "name") var name: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "price") var price: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "img") var img: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "category") var category: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "description") var description: String
) : BaseObservable() {



}
