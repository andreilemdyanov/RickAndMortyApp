package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.model.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): EpisodeResponse
}