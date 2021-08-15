package com.example.rickandmortyapp.data.model

import com.example.rickandmortyapp.data.network.model.LocationResponse
import com.example.rickandmortyapp.data.network.model.OriginResponse

data class Hero(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: OriginResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)