package com.example.rickandmortyapp.presentation.character_details.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.MainActivity
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailsBinding

class FragmentCharacterDetails : Fragment(R.layout.fragment_character_details) {

    private val binding by viewBinding(FragmentCharacterDetailsBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getParcelable<Hero>(HERO)?.let {
                with(binding) {
                    tvNameDetails.text = it.name
                    Glide.with(requireContext())
                        .load(it.image)
                        .placeholder(R.drawable.place_holder)
                        .centerCrop()
                        .into(ivAvatarDetails)

                    tvStatusDetails.text =
                        requireContext().getString(R.string.status, it.status, it.species)
                    when (it.status.trim()) {
                        "Alive" -> ivCircleDetails.setImageDrawable(
                            AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.alive
                            )
                        )
                        "Dead" -> ivCircleDetails.setImageDrawable(
                            AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.dead
                            )
                        )
                        else -> ivCircleDetails.setImageDrawable(
                            AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.unknown
                            )
                        )
                    }
                    tvGenderDetails.text = it.gender
                    tvOriginDetails.text = it.origin.name
                    tvLocationDetails.text = it.location.name
                    tvTypeDetails.text = it.type
                    tvDimensionDetails.text = "" //TODO
                    tvEpisodesDetails.text = it.episodesCount.toString()
                    btnBackDetails.setOnClickListener {
                        (activity as MainActivity).onBackClick()
                    }
                }
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    companion object {
        private const val HERO = "hero"

        @JvmStatic
        fun newInstance(hero: Hero) = FragmentCharacterDetails().apply {
            arguments = Bundle().apply {
                putParcelable(HERO, hero)
            }
        }

    }
}