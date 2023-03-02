package com.example.rickandmortyapp.presentation.character_list.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.presentation.character_list.view.HeroesAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HeroesViewModel(repo: HeroesRepository) : ViewModel() {

    private val heroes: Flow<PagingData<Hero>> = repo.getHeroes()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun setData(lifecycle: Lifecycle, adapter: HeroesAdapter) {
        viewModelScope.launch {
            heroes.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }
    }
}