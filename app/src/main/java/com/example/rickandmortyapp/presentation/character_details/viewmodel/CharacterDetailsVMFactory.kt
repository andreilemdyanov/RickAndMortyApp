package com.example.rickandmortyapp.presentation.character_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.data.HeroesRepository
import javax.inject.Inject

class CharacterDetailsVMFactory @Inject constructor(private val repo: HeroesRepository) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(repo::class.java).newInstance(repo)
    }
}