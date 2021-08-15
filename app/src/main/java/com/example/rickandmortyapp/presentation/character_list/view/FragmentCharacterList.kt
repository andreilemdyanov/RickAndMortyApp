package com.example.rickandmortyapp.presentation.character_list.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.extensions.dpToIntPx
import com.example.rickandmortyapp.presentation.character_list.viewmodel.HeroesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import java.net.UnknownHostException

class FragmentCharacterList : Fragment(R.layout.fragment_character_list) {

    private var clickListener: ClickListener? = null
    private val binding by viewBinding(FragmentCharacterListBinding::bind)
    private val viewModel: HeroesViewModel by activityViewModels()
    private val adapter by lazy { HeroesAdapter(clickListenerItem) }
    private lateinit var job: Job
    private var orientationLand: Boolean = false

    private val clickListenerItem = { hero: Hero? ->
        if (hero != null)
            binding.rvCharacter.let {
                clickListener?.onHeroDetailsClick(hero)
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
            clickListener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvCharacter.adapter = adapter.withLoadStateHeaderAndFooter(
                header = HeroesLoaderStateAdapter(),
                footer = HeroesLoaderStateAdapter()
            )
            val manager =
                GridLayoutManager(requireContext(), resources.getInteger(R.integer.grid_count))
            rvCharacter.layoutManager = manager
            rvCharacter.addItemDecoration(
                if (orientationLand) LinearSpacingItemDecoration(requireContext().dpToIntPx(8)) else GridSpacingItemDecoration(
                    requireContext().dpToIntPx(8)
                )
            )
            adapter.addLoadStateListener { state: CombinedLoadStates ->
                rvCharacter.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    override fun onStart() {
        super.onStart()
        job = CoroutineScope(Dispatchers.Main).launch(CoroutineExceptionHandler { _, exception ->
            Log.d("FragmentCharacterList", "$exception")
            when (exception) {
                is UnknownHostException -> Snackbar.make(
                    binding.root,
                    "Нет соединения с интернетом",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }) {
            viewModel.heroes.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        orientationLand = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCharacterList()
    }

    interface ClickListener {
        fun onHeroDetailsClick(hero: Hero)
    }
}