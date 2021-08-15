package com.example.rickandmortyapp.presentation.character_list.view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.data.model.Hero

class HeroesDiffCallback : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}
