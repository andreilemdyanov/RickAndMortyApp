package com.example.rickandmortyapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.rickandmortyapp.data.network.CacheInterceptor
import com.example.rickandmortyapp.data.network.RetrofitModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

class App : Application() {

    private val client by lazy {
        OkHttpClient().newBuilder()
            .cache(
                Cache(
                    directory = File(cacheDir, "http_cache"),
                    maxSize = 50L * 1024L * 1024L
                )
            )
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(CacheInterceptor())
            .build()
    }

    val retrofit by lazy { RetrofitModule(client) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun hasNetwork(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.activeNetwork?.run {
                val nc = cm.getNetworkCapabilities(this)
                nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            } ?: false
        } else {
            cm.activeNetworkInfo?.run { isConnectedOrConnecting } ?: false
        }

    }

    companion object {
        lateinit var instance: App
    }
}