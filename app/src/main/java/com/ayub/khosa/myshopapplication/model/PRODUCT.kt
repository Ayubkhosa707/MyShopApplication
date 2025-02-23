package com.ayub.khosa.myshopapplication.model

import androidx.databinding.BaseObservable
import com.ayub.khosa.myshopapplication.BR
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import java.io.Serializable

class PRODUCT() : BaseObservable(), Serializable {


    constructor(
        name: String,
        img: String,
        category: String,
        description: String,
        price: String,
        id: String
    ) : this() {
        this.name = name
        this.img = img
        this.id = id
        this.description = description
        this.category = category
        this.price = price
    }

    @androidx.databinding.Bindable
    var name: String = "PRODUCT  name"
        set(name: String) {
            field = name
            PrintLogs.printD("PRODUCT set  name" + name)
            notifyPropertyChanged(BR.name)
        }

    @androidx.databinding.Bindable
    var img: String = "PRODUCT img"
        set(img: String) {
            field = img
            PrintLogs.printD("PRODUCT set img" + img)
            notifyPropertyChanged(BR.img)
        }

    @androidx.databinding.Bindable
    var category: String = "PRODUCT category"
        set(category: String) {
            field = category
            PrintLogs.printD("PRODUCT set category" + category)
            notifyPropertyChanged(BR.category)
        }

    @androidx.databinding.Bindable
    var description: String = "PRODUCT description"
        set(description: String) {
            field = description
            PrintLogs.printD("PRODUCT set description" + description)
            notifyPropertyChanged(BR.description)
        }


    @androidx.databinding.Bindable
    var id: String = "0"
        set(id: String) {
            field = id
            PrintLogs.printD("PRODUCT set  id  ----- " + id)
            notifyPropertyChanged(BR.id)
        }

    @androidx.databinding.Bindable
    var price: String = "0"
        set(price: String) {
            field = price
            PrintLogs.printD("PRODUCT set  price  ----- " + price)
            notifyPropertyChanged(BR.price)
        }


}