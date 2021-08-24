package com.example.rickandmortyapp.presentation.character_details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.LocationDetail
import com.example.rickandmortyapp.data.network.model.toLocationDetail
import io.reactivex.Single

class HeroesDetailsViewModel(repo: HeroesRepository, id: Int) : ViewModel() {

    val location: Single<LocationDetail> = repo.getLocation(id).map { it.toLocationDetail() }
}