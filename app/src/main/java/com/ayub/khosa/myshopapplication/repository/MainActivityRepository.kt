package com.ayub.khosa.myshopapplication.repository

import android.content.Context
import android.os.AsyncTask
import com.ayub.khosa.myshopapplication.model.APIResponce
import com.ayub.khosa.myshopapplication.model.APIResponceUser
import com.ayub.khosa.myshopapplication.api.RetrofitBuilder
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.ListCATEGORYS
import com.ayub.khosa.myshopapplication.model.ListPRODUCTS
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.room.AppDatabase
import com.ayub.khosa.myshopapplication.room.ShopDAO
import com.ayub.khosa.myshopapplication.utils.PrintLogs


class MainActivityRepository(context: Context) {
    private val apiService = RetrofitBuilder.apiService
    var db: ShopDAO = AppDatabase.getInstance(context)?.shopDAO()!!
    suspend fun getAllProducts() = apiService.get_ListPRODUCTS("ListPRODUCTS")

    suspend fun getAllCategorys() = apiService.get_ListCATEGORYS("ListCATEGORYS")

    suspend fun getLoginUser(email: String, password: String): APIResponceUser =
        apiService.getLogin("btn-login", email, password)

    suspend fun is_logged_in(): APIResponce = apiService.is_logged_in("is_logged_in")

    fun getProducts_DB(): ListPRODUCTS {

        val response: List<PRODUCT> = db.gelAllProducts()
        PrintLogs.printD("MainActivityRepository getProducts_DB  " + response.size)
        var data = ArrayList<PRODUCT>()


        for (i in response.indices) {
            data.add(response[i])

        }
        return ListPRODUCTS(data)
    }

    fun deleteProductinDB(product: PRODUCT) {
        db.deleteProduct(product)
    }

    fun insertProductinDB(product: PRODUCT) {
        insertProductAsyncTask(db).execute(product)
    }

    fun updateProductinDB(product: PRODUCT) {
        db.updateProduct(product)
    }


    fun fetchProductByName(name: String, product_id: String): PRODUCT {
        return db.fetchProductByName(name = name, product_id = product_id)
    }

    private class insertProductAsyncTask internal constructor(private val shopDAO: ShopDAO) :
        AsyncTask<PRODUCT, Void, Void>() {

        override fun doInBackground(vararg params: PRODUCT): Void? {
            shopDAO.insertProduct(params[0])
            return null
        }
    }


    fun getCategrys_DB(): ListCATEGORYS {

        val response: List<CATEGORY> = db.getAllCategorys()
        PrintLogs.printD("MainActivityRepository getCategrys_DB  " + response.size)
        var data = ArrayList<CATEGORY>()


        for (i in response.indices) {
            data.add(response[i])

        }
        return ListCATEGORYS(data)
    }

    fun insertCategoryinDB(category: CATEGORY) {
        insertCategoryAsyncTask(db).execute(category)
    }

    fun updateCategoryinDB(category: CATEGORY) {
        db.updateCategory(category)
    }

    fun deleteCategoryinDB(category: CATEGORY) {
        db.deleteCategory(category)
    }

    fun fetchCategoryByName(name: String, category_id: String): CATEGORY {
        return db.fetchCategoryByName(name = name, category_id = category_id)
    }

    private class insertCategoryAsyncTask internal constructor(private val shopDAO: ShopDAO) :
        AsyncTask<CATEGORY, Void, Void>() {

        override fun doInBackground(vararg params: CATEGORY): Void? {
            shopDAO.insertCategory(params[0])
            return null
        }
    }


    fun insertUSERinDB(user: USER) {
        insertUSERAsyncTask(db).execute(user)
    }

    fun updateUSERinDB(user: USER) {
        db.updateUSER(user)
    }


    fun fetchUSERByName(email_id: String, password: String): USER {
        return db.fetchUserByName(email_id, password)
    }

    private class insertUSERAsyncTask internal constructor(private val shopDAO: ShopDAO) :
        AsyncTask<USER, Void, Void>() {

        override fun doInBackground(vararg params: USER): Void? {
            shopDAO.insertUSER(params[0])
            return null
        }
    }
}