package com.ayub.khosa.myshopapplication.api

import android.webkit.CookieManager
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieHandler
import java.net.CookiePolicy
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

    lateinit var cookieManager: CookieManager

    private const val BASE_URL = "https://ayubkhosa.com/"
    private val myHttpClient: OkHttpClient by lazy {
        PrintLogs.printD("------------ new OKHttpClient ------------")


        // init cookie manager

        CookieHandler.setDefault(java.net.CookieManager(null, CookiePolicy.ACCEPT_ALL))

        val ins = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .cookieJar(object : CookieJar {

                /**
                 * @param url
                 * @param cookies list of cookies get in api response
                 */
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                    cookieManager = CookieManager.getInstance()
                    for (cookie in cookies) {
                        cookieManager.setCookie(url.toString(), cookie.toString())
                        PrintLogs.printD(
                            "saveFromResponse :  Cookie url : " + url.toString() + cookie.toString()
                        )
                    }
                }

                /**
                 * @param url
                 *
                 * adding cookies with request
                 */
                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    cookieManager = CookieManager.getInstance()

                    val cookies: ArrayList<Cookie> = ArrayList()
                    if (cookieManager.getCookie(url.toString()) != null) {
                        val splitCookies =
                            cookieManager.getCookie(url.toString()).split("[,;]".toRegex())
                                .dropLastWhile { it.isEmpty() }.toTypedArray()
                        for (i in splitCookies.indices) {
                            cookies.add(Cookie.parse(url, splitCookies[i].trim { it <= ' ' })!!)
                            PrintLogs.printD(
                                "loadForRequest :Cookie.add ::  " + Cookie.parse(
                                    url,
                                    splitCookies[i].trim { it <= ' ' })!!
                            )
                        }
                    }
                    return cookies
                }
            })
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