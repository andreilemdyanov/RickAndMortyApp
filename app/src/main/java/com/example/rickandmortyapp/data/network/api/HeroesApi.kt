package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.HeroesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {

    @GET("character")
    fun fetchResultsRx(@Query("page") page: Int = 1): Observable<HeroesResponse>
}