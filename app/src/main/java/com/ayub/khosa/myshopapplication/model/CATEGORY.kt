package com.ayub.khosa.myshopapplication.model

import androidx.databinding.BaseObservable
import com.ayub.khosa.myshopapplication.BR
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import java.io.Serializable

class CATEGORY() : BaseObservable(), Serializable {


    constructor(name: String, img: String, id: String) : this() {
        this.name = name
        this.img = img
        this.id = id
    }

    @androidx.databinding.Bindable
    var name: String = "CATEGORY  name"
        set(name: String) {
            field = name
            PrintLogs.printD("CATEGORY set  name" + name)
            notifyPropertyChanged(BR.name)
        }

    @androidx.databinding.Bindable
    var img: String = "CATEGORY img"
        set(img: String) {
            field = img
            PrintLogs.printD("CATEGORY set img" + img)
            notifyPropertyChanged(BR.img)
        }

    @androidx.databinding.Bindable
    var id: String = "0"
        set(id: String) {
            field = id
            PrintLogs.printD("CATEGORY set  id  ----- " + id)
            notifyPropertyChanged(BR.id)
        }


}
