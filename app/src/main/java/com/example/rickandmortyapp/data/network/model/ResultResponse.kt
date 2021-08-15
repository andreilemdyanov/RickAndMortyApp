package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Hero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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

fun ResultResponse.toHero() =
    Hero(
        created,
        episode,
        gender,
        id,
        image,
        location,
        name,
        origin,
        species,
        status,
        type,
        url
    )