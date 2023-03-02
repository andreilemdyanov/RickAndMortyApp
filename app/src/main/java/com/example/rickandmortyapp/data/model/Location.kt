package com.example.rickandmortyapp.data.model

import android.os.Parcelable
import com.example.rickandmortyapp.extensions.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val name: String = EMPTY_STRING,
    val url: String = EMPTY_STRING
) : Parcelable