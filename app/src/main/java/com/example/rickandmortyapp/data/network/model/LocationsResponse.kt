package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsResponse (
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val results: List<LocationDetailResponse>
)
