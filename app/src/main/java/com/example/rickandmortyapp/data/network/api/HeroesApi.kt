package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.HeroesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {
    @GET("character")
    suspend fun fetchResults(@Query("page") page: Int = 1): Response<HeroesResponse>

    @GET("character")
    fun fetchResultsRx(@Query("page") page: Int = 1): Single<HeroesResponse>
}