package com.example.rickandmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.model.LocationDetail
import com.example.rickandmortyapp.data.network.HeroesPageSource
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.api.LocationApi
import kotlinx.coroutines.flow.Flow

class HeroesRepository(
    private val charactersApi: HeroesApi,
    private val episodeApi: EpisodeApi,
    private val locationApi: LocationApi
) {

    fun getHeroes(): Flow<PagingData<Hero>> {
        return Pager(PagingConfig(20),
            pagingSourceFactory = { HeroesPageSource(charactersApi, episodeApi) }
        ).flow
    }

    suspend fun getLocation(id: Int): LocationDetail {
        return locationApi.fetchLocation(id).transform()
    }
}