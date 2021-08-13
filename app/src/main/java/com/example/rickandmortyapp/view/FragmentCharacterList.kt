package com.example.rickandmortyapp.view

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding

class FragmentCharacterList : Fragment(R.layout.fragment_character_list) {
    private val binding by viewBinding(FragmentCharacterListBinding::bind)

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCharacterList()
    }
}