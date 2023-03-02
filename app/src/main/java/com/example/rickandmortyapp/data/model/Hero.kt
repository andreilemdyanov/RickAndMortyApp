package com.example.rickandmortyapp.data.model

import android.os.Parcelable
import com.example.rickandmortyapp.extensions.EMPTY_INT
import com.example.rickandmortyapp.extensions.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val created: String = EMPTY_STRING,
    val firstEpisode: String = EMPTY_STRING,
    val episodesCount: Int = EMPTY_INT,
    val gender: String = EMPTY_STRING,
    val id: Int = EMPTY_INT,
    val image: String = EMPTY_STRING,
    val location: Location = Location(),
    val name: String = EMPTY_STRING,
    val origin: Origin = Origin(),
    val species: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val url: String = EMPTY_STRING
) : Parcelable