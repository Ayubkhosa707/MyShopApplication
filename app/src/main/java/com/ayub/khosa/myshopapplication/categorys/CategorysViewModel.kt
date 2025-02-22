package com.ayub.khosa.myshopapplication.categorys

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS

class CategorysViewModel : ViewModel() {

    var listCategorysData: MutableLiveData<ListCATEGORYS> = MutableLiveData()
    var categoryRecyclerViewAdapter: CategoryRecyclerViewAdapter = CategoryRecyclerViewAdapter()

    init {
        var product = CATEGORY("name", "img")
        val data = kotlin.collections.ArrayList<CATEGORY>()
        data.add(product)
        setAdapterData(data)
    }

    fun getAdapter(): CategoryRecyclerViewAdapter {
        return categoryRecyclerViewAdapter
    }

    fun setAdapterData(data: ArrayList<CATEGORY>) {
        categoryRecyclerViewAdapter.setDataList(data)
        categoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ListCATEGORYS> {
        return listCategorysData
    }
}