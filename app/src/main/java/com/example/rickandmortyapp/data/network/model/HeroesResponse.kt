package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Heroes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val results: List<CharacterResponse>
)

fun HeroesResponse.toHeroes() =
    Heroes(
        info.count, results.map { it.toHero() })
