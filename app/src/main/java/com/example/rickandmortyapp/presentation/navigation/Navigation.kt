package com.example.rickandmortyapp.presentation.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.presentation.character_details.view.FragmentCharacterDetails
import com.example.rickandmortyapp.presentation.character_list.view.FragmentCharacterList

class Navigation(
    private val savedInstanceState: Bundle?,
    private val supportFragmentManager: FragmentManager
) {

    fun init() {
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    FragmentCharacterList.newInstance(),
                    HEROES_FRAGMENT_TAG
                )
                .commit()
        else supportFragmentManager.findFragmentByTag(HEROES_FRAGMENT_TAG) as? FragmentCharacterList
    }

    fun navigateDetails(hero: Hero){
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                FragmentCharacterDetails.newInstance(hero)
            )
            .addToBackStack(null)
            .commit()
    }

    fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val HEROES_FRAGMENT_TAG = "heroesFragment"
    }
}