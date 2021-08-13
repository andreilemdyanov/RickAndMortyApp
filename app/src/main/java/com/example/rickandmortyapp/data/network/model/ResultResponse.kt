package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName

data class ResultResponse(
    @SerialName("created")
    val created: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: LocationResponse,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: OriginResponse,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)