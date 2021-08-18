package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.LocationsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("location")
    fun fetchResults(@Query("page") page: Int = 1): Observable<LocationsResponse>
}