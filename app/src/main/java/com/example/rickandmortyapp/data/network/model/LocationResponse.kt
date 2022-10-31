package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Location
import com.example.rickandmortyapp.data.network.Transformable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) : Transformable<Location> {
    override fun transform() =
        Location(name, url)
}