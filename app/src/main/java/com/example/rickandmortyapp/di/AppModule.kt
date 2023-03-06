package com.example.rickandmortyapp.di

import android.content.Context
import com.example.rickandmortyapp.presentation.character_details.view.ResourceManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideResourceManager(): ResourceManager = ResourceManager.Base(context)
}