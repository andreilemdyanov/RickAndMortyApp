package com.example.rickandmortyapp.data.network.model

import kotlinx.serialization.SerialName

data class InfoResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("pages")
    val pages: Int,
    @SerialName("prev")
    val prev: Any
)