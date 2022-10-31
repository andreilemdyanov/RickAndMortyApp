package com.example.rickandmortyapp.data.network.model

import com.example.rickandmortyapp.data.model.Heroes
import com.example.rickandmortyapp.data.network.Transformable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val results: List<CharacterResponse>
) : Transformable<Heroes> {
    override fun transform() =
        Heroes(
            pagesCount = info.count,
            list = results.map { it.transform() })
}
