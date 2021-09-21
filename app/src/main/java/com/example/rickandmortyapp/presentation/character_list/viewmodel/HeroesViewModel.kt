package com.example.rickandmortyapp.presentation.character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HeroesViewModel(repo: HeroesRepository) : ViewModel() {

    val heroes: Flow<PagingData<Hero>> = repo.getHeroes()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}