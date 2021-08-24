package com.example.rickandmortyapp.presentation.character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import io.reactivex.Flowable

class HeroesViewModel(repo: HeroesRepository) : ViewModel() {

    val heroes: Flowable<PagingData<Hero>> = repo.getHeroes()
        .cachedIn(viewModelScope)
}