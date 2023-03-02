package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.presentation.character_details.view.FragmentCharacterDetails
import com.example.rickandmortyapp.presentation.character_list.view.FragmentCharacterList
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun injectFragmentCharacterList(fragmentCharacterList: FragmentCharacterList)
    fun injectFragmentCharacterDetails(fragmentCharacterDetails: FragmentCharacterDetails)
}