package com.example.rickandmortyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDetail(
    val name: String,
    val url: String,
    val dimension: String
) : Parcelable
