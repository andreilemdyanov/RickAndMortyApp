package com.example.rickandmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.HeroesPageSource
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.api.LocationApi
import com.example.rickandmortyapp.data.network.model.LocationDetailResponse
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HeroesRepository(
    private val charactersApi: HeroesApi,
    private val episodeApi: EpisodeApi,
    private val locationApi: LocationApi
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getHeroes(): Flowable<PagingData<Hero>> {
        return Pager(PagingConfig(20),
            pagingSourceFactory = { HeroesPageSource(charactersApi, episodeApi) }
        ).flowable
    }

    fun getLocation(id: Int): Single<LocationDetailResponse> {
        return locationApi.fetchLocation(id)
    }
}