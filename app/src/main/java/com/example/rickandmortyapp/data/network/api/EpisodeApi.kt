package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.EpisodeResponse
import com.example.rickandmortyapp.data.network.model.EpisodesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {
    @GET("episode")
    fun fetchResults(@Query("page") page: Int = 1): Observable<EpisodesResponse>

    @GET("episode/{id}")
    fun getEpisode(@Path("id") id: Int): Single<EpisodeResponse>
}