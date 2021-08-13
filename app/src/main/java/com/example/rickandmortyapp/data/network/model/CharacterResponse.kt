package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName

data class CharacterResponse(
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val results: List<ResultResponse>
)