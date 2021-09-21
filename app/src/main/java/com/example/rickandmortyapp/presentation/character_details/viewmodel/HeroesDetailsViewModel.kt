package com.example.rickandmortyapp.presentation.character_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.LocationDetail
import com.example.rickandmortyapp.data.network.model.toLocationDetail
import kotlinx.coroutines.launch

class HeroesDetailsViewModel(private val repo: HeroesRepository) : ViewModel() {

    private val _location = MutableLiveData<LocationDetail>()
    val location: LiveData<LocationDetail> get() = _location

    fun getLocation(id: Int) {
        viewModelScope.launch {
            _location.postValue(repo.getLocation(id).toLocationDetail())
        }
    }
}