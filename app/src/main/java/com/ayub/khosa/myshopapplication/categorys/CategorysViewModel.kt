package com.ayub.khosa.myshopapplication.categorys

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class CategorysViewModel : ViewModel() {

    var listCategorysData: MutableLiveData<ListCATEGORYS> = MutableLiveData()
    var categoryRecyclerViewAdapter: CategoryRecyclerViewAdapter = CategoryRecyclerViewAdapter()


    fun getcatgorys() : MutableLiveData<ListCATEGORYS> {
        listCategorysData = MainActivityRepository.getlistCATEGORYApiCall()
        return listCategorysData
    }
    init {

        PrintLogs.printD("CategorysViewModel  init")
        setAdapterData(getcatgorys())
    }

    fun getAdapter(): CategoryRecyclerViewAdapter {
        return categoryRecyclerViewAdapter
    }

    fun setAdapterData(data: MutableLiveData<ListCATEGORYS>) {
        categoryRecyclerViewAdapter.setDataList(data)
        categoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ListCATEGORYS> {
        return listCategorysData
    }
}