package com.example.rickandmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.HeroesPageSource
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.api.LocationApi
import com.example.rickandmortyapp.data.network.model.EpisodesResponse
import com.example.rickandmortyapp.data.network.model.LocationsResponse
import com.example.rickandmortyapp.data.network.model.toEpisode
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class HeroesRepository(
    private val charactersApi: HeroesApi,
    private val episodeApi: EpisodeApi,
    private val locationApi: LocationApi
) {

    fun getHeroes(): Flowable<PagingData<Hero>> {
        return Pager(PagingConfig(20),
            pagingSourceFactory = { HeroesPageSource(charactersApi) }
        ).flowable
    }

    fun getEpisodes(page: Int = 1): Observable<EpisodesResponse> {
        return episodeApi.fetchResults(page)
    }

    fun getEpisode(id: Int): Single<Episode> {
        return episodeApi.getEpisode(id).map { it.toEpisode(it.id, it.name, it.url) }
    }

    fun getLocations(page: Int = 1): Observable<LocationsResponse> {
        return locationApi.fetchResults(page)
    }
}