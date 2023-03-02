package com.example.rickandmortyapp.di

import android.content.Context
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.network.CacheInterceptor
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.api.LocationApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    fun provideRepository(retrofit: Retrofit): HeroesRepository {
        return HeroesRepository(
            RetrofitModule(retrofit).heroesApi,
            RetrofitModule(retrofit).episodeApi,
            RetrofitModule(retrofit).locationApi
        )
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

    @Provides
    fun provideretrofit(json: Json, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    private class RetrofitModule(retrofit: Retrofit) {
        val heroesApi: HeroesApi = retrofit.create()
        val episodeApi: EpisodeApi = retrofit.create()
        val locationApi: LocationApi = retrofit.create()
    }
}