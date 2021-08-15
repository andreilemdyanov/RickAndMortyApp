package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val results: List<ResultResponse>
)