package com.example.rickandmortyapp.presentation.character_details.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.MainActivity
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmortyapp.presentation.character_details.viewmodel.CharacterDetailsVMFactory
import com.example.rickandmortyapp.presentation.character_details.viewmodel.HeroesDetailsViewModel
import javax.inject.Inject

class FragmentCharacterDetails : Fragment(R.layout.fragment_character_details) {

    private val binding by viewBinding(FragmentCharacterDetailsBinding::bind)

    @Inject
    lateinit var viewModelFactory: CharacterDetailsVMFactory
    val viewModel by lazy {
        ViewModelProvider(this@FragmentCharacterDetails, viewModelFactory)
            .get(HeroesDetailsViewModel::class.java)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.injectFragmentCharacterDetails(
            this
        )
        arguments?.apply {
            getParcelable<Hero>(HERO)?.let {
                if (it.location.url.isNotBlank()) {
                    viewModel.getLocation(it.location.url.substringAfterLast("/").toInt())
                }
                with(binding) {
                    tvNameDetails.text = it.name
                    Glide.with(requireContext())
                        .load(it.image)
                        .placeholder(R.drawable.place_holder)
                        .centerCrop()
                        .into(ivAvatarDetails)

                    tvStatusDetails.text =
                        requireContext().getString(R.string.status, it.status, it.species)
                    setStatusPicture(it.status, ivCircleDetails)
                    tvGenderDetails.text = it.gender
                    tvOriginDetails.text = it.origin.name
                    tvLocationDetails.text = it.location.name
                    tvTypeDetails.text = it.type
                    tvEpisodesDetails.text = it.episodesCount.toString()
                    btnBackDetails.setOnClickListener {
                        (activity as MainActivity).onBackClick()
                    }
                }
            }
        }
    }

    private fun setStatusPicture(status: String, view: ImageView) {
        val resource = when (status.trim()) {
            "Alive" -> R.drawable.alive
            "Dead" -> R.drawable.dead
            else -> R.drawable.unknown
        }
        view.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                resource
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onStart() {
        super.onStart()
        viewModel.location.observe(viewLifecycleOwner) {
            binding.tvDimensionDetails.text = it.dimension
        }
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