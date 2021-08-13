package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName

data class OriginResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)