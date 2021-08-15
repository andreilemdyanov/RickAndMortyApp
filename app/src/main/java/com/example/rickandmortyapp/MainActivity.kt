package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.presentation.character_list.view.FragmentCharacterList

class MainActivity : AppCompatActivity(), FragmentCharacterList.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val HEROES_FRAGMENT_TAG = "heroesFragment"
    }

    override fun onHeroDetailsClick(hero: Hero) {
//        Log.d(this.localClassName, hero.id.toString())
    }
}