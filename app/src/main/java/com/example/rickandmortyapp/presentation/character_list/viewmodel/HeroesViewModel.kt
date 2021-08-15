package com.example.rickandmortyapp.presentation.character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.RetrofitModule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HeroesViewModel : ViewModel() {

    private val retrofitModule: RetrofitModule = App.instance.retrofit
    private val repo by lazy { HeroesRepository(retrofitModule.heroesApi) }

    val heroes: StateFlow<PagingData<Hero>> = repo.getHeroes()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}