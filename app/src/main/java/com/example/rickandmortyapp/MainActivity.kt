package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.view.FragmentCharacterList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    FragmentCharacterList.newInstance(),
                    CHARACTER_FRAGMENT_TAG
                )
                .commit()
        else supportFragmentManager.findFragmentByTag(CHARACTER_FRAGMENT_TAG) as? FragmentCharacterList
    }

    fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val CHARACTER_FRAGMENT_TAG = "characterFragment"
    }
}