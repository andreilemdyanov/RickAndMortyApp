package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.Transformable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
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
) : Transformable<Hero> {
    override fun transform() =
        Hero(
            created = created,
            firstEpisode = episode.first(),
            episodesCount = episode.size,
            gender = gender,
            id = id,
            image = image,
            location = location.transform(),
            name = name,
            origin = origin.transform(),
            species = species,
            status = status,
            type = type.ifBlank { "unknown" },
            url = url
        )

}