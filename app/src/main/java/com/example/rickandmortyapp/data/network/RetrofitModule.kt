package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.api.LocationApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class RetrofitModule(client: OkHttpClient) {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val heroesApi: HeroesApi = retrofit.create()
    val episodeApi: EpisodeApi = retrofit.create()
    val locationApi: LocationApi = retrofit.create()
}