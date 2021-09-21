package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.LocationDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("location/{id}")
    suspend fun fetchLocation(@Path("id") id: Int): LocationDetailResponse
}