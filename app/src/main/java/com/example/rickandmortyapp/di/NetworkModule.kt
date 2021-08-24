package com.example.rickandmortyapp.di

import android.content.Context
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.network.CacheInterceptor
import com.example.rickandmortyapp.data.network.RetrofitModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    fun provideRepository(): HeroesRepository {
        return HeroesRepository(
            App.instance.retrofit.heroesApi,
            App.instance.retrofit.episodeApi,
            App.instance.retrofit.locationApi
        )
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): RetrofitModule {
        return RetrofitModule(client)
    }

    @Provides
    fun provideClient(cache: Cache): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cache(cache)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(CacheInterceptor())
            .build()
    }

    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 50L * 1024L * 1024L); //10 MB
    }

    @Provides
    fun provideFile(context: Context): File {
        return File(context.cacheDir, "http_cache")
    }

}