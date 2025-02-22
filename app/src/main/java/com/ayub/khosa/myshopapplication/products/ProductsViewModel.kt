package com.ayub.khosa.myshopapplication.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT

class ProductsViewModel : ViewModel() {

    var listPRODUCTSData: MutableLiveData<ListPRODUCTS> = MutableLiveData()
    var productRecyclerViewAdapter: ProductRecyclerViewAdapter = ProductRecyclerViewAdapter()

    init {
        var product = PRODUCT("name", "img", "category", "description", "0")
        val data = kotlin.collections.ArrayList<PRODUCT>()
        data.add(product)
        setAdapterData(data)
    }

    fun getAdapter(): ProductRecyclerViewAdapter {
        return productRecyclerViewAdapter
    }

    fun setAdapterData(data: ArrayList<PRODUCT>) {
        productRecyclerViewAdapter.setDataList(data)
        productRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ListPRODUCTS> {
        return listPRODUCTSData
    }
}