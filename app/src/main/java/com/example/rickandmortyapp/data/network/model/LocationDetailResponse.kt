package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LocationDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("dimension")
    val dimension: String,
    @SerialName("residents")
    val residents: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String
)
