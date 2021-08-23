package com.example.rickandmortyapp.presentation.character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.RetrofitModule
import com.example.rickandmortyapp.data.network.model.LocationsResponse
import io.reactivex.Flowable
import io.reactivex.Observable

class HeroesViewModel : ViewModel() {

    private val retrofitModule: RetrofitModule = App.instance.retrofit
    private val repo by lazy {
        HeroesRepository(
            retrofitModule.heroesApi,
            retrofitModule.episodeApi,
            retrofitModule.locationApi
        )
    }

    val heroes: Flowable<PagingData<Hero>> = repo.getHeroes()
        .cachedIn(viewModelScope)

    val locations: Observable<LocationsResponse> = repo.getLocations(1)
        .mergeWith(repo.getLocations(2))
        .mergeWith(repo.getLocations(3))
        .mergeWith(repo.getLocations(4))
        .mergeWith(repo.getLocations(5))
        .mergeWith(repo.getLocations(6))
}