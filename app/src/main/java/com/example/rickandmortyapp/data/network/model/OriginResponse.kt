package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Origin
import com.example.rickandmortyapp.data.network.Transformable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) : Transformable<Origin> {
    override fun transform() =
        Origin(name, url)
}