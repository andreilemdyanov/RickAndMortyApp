package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.CharacterResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    fun fetchResults(@Query("page") page: Int = 1): Observable<Response<CharacterResponse>>
}