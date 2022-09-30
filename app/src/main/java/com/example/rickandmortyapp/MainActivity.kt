package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.presentation.character_list.view.FragmentCharacterList
import com.example.rickandmortyapp.presentation.navigation.Navigation

class MainActivity : AppCompatActivity(), FragmentCharacterList.ClickListener {

    lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = Navigation(savedInstanceState, supportFragmentManager)
        navigation.init()
    }

    fun onBackClick() {
        navigation.onBackClick()
    }

    override fun onHeroDetailsClick(hero: Hero) {
        navigation.navigateDetails(hero)
    }
}