package com.example.rickandmortyapp.data.network

interface Transformable<T> {
    fun transform(): T
}