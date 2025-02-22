package com.ayub.khosa.myshopapplication.api

import com.ayub.khosa.myshopapplication.utils.PrintLogs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}

object RetrofitClient {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private const val BASE_URL = "https://ayubkhosa.com/"
    private val myHttpClient: OkHttpClient by lazy {
        PrintLogs.printD("------------ new OKHttpClient ------------")
        val ins = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
        ins.build()
    }
    val retrofit: Retrofit by lazy {
        PrintLogs.printD("------ new Retrofit retrofitInstance  ----------")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(myHttpClient)
            .build()
    }
}