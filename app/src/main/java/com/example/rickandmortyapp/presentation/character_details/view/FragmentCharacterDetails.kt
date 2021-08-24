package com.example.rickandmortyapp.presentation.character_details.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.MainActivity
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.HeroesRepository
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmortyapp.presentation.character_details.viewmodel.CharacterDetailsVMFactory
import com.example.rickandmortyapp.presentation.character_details.viewmodel.HeroesDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentCharacterDetails : Fragment(R.layout.fragment_character_details) {

    private val binding by viewBinding(FragmentCharacterDetailsBinding::bind)
    lateinit var viewModelFactory: CharacterDetailsVMFactory
    lateinit var viewModel: HeroesDetailsViewModel
    private val disposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getParcelable<Hero>(HERO)?.let {
                if (it.location.url.isNotBlank()) {
                    viewModelFactory = CharacterDetailsVMFactory(
                        HeroesRepository(
                            App.instance.retrofit.heroesApi,
                            App.instance.retrofit.episodeApi,
                            App.instance.retrofit.locationApi
                        ), it.location.url.substringAfterLast("/").toInt()
                    )
                    viewModel = ViewModelProvider(this@FragmentCharacterDetails, viewModelFactory)
                        .get(HeroesDetailsViewModel::class.java)
                    disposable.add(
                        viewModel.location
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ location ->
                                binding.tvDimensionDetails.text = location.dimension
                            }, { th ->
                                Snackbar.make(
                                    requireContext(),
                                    view,
                                    th.localizedMessage.toString(),
                                    Snackbar.LENGTH_LONG
                                ).show()
                            })
                    )
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
                    tvTypeDetails.text = it.type.ifBlank { "unknown" }
                    tvDimensionDetails.text.ifBlank { "unknown" }
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

    override fun onPause() {
        super.onPause()
        disposable.dispose()
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