package com.example.rickandmortyapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.rickandmortyapp.data.network.RetrofitModule
import com.example.rickandmortyapp.di.AppComponent
import com.example.rickandmortyapp.di.AppModule
import com.example.rickandmortyapp.di.DaggerAppComponent
import okhttp3.OkHttpClient
import javax.inject.Inject


class App : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var client: OkHttpClient

    @Inject
    lateinit var retrofit: RetrofitModule

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.injectApp(this)
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