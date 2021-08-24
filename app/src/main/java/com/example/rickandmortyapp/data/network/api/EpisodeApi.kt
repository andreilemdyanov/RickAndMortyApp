package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.EpisodeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode/{id}")
    fun getEpisode(@Path("id") id: Int): Single<EpisodeResponse>
}