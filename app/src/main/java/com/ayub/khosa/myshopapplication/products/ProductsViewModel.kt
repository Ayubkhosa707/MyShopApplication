package com.ayub.khosa.myshopapplication.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class ProductsViewModel : ViewModel() {

    var listPRODUCTSData: MutableLiveData<ListPRODUCTS> = MutableLiveData()
    var productRecyclerViewAdapter: ProductRecyclerViewAdapter = ProductRecyclerViewAdapter()


    fun getProducts() : MutableLiveData<ListPRODUCTS> {
        listPRODUCTSData = MainActivityRepository.getlistPRODUCTApiCall()
        return listPRODUCTSData
    }
    init {
        PrintLogs.printD("ProductsViewModel  init")
        listPRODUCTSData = getProducts()
        setAdapterData(listPRODUCTSData)
    }

    fun getProductsViewModelData() {
        PrintLogs.printD("getProductsViewModelData ")
        listPRODUCTSData = getProducts()
        setAdapterData(listPRODUCTSData)


    }


    fun getAdapter(): ProductRecyclerViewAdapter {
        return productRecyclerViewAdapter
    }

    fun setAdapterData(data: MutableLiveData<ListPRODUCTS>?) {
        if (data != null) {
            productRecyclerViewAdapter.setDataList(data)
        }
        productRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ListPRODUCTS> {
        return listPRODUCTSData
    }
}