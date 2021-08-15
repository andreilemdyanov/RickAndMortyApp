package com.example.rickandmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.HeroesPageSource
import com.example.rickandmortyapp.data.network.api.HeroesApi
import kotlinx.coroutines.flow.Flow

class HeroesRepository(private val charactersApi: HeroesApi) {

    fun getHeroes(): Flow<PagingData<Hero>> {
        return Pager(PagingConfig(20),
            pagingSourceFactory = { HeroesPageSource(charactersApi) }
        ).flow
    }
}