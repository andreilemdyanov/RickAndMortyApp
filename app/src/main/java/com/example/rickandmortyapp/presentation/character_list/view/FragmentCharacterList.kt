package com.example.rickandmortyapp.presentation.character_list.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.App
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.extensions.dpToIntPx
import com.example.rickandmortyapp.presentation.character_list.viewmodel.CharacterListVMFactory
import com.example.rickandmortyapp.presentation.character_list.viewmodel.HeroesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentCharacterList : Fragment(R.layout.fragment_character_list) {

    private var clickListener: ClickListener? = null
    private val binding by viewBinding(FragmentCharacterListBinding::bind)

    @Inject
    lateinit var viewModelFactory: CharacterListVMFactory
    lateinit var viewModel: HeroesViewModel
    private val adapter by lazy { HeroesAdapter(clickListenerItem) }
    private var orientationLand: Boolean = false

    private val clickListenerItem = { hero: Hero ->
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
        val context = requireContext()
        (context.applicationContext as App).appComponent.injectFragmentCharacterList(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HeroesViewModel::class.java)
        with(binding) {
            rvCharacter.adapter = adapter.withLoadStateHeaderAndFooter(
                header = HeroesLoaderStateAdapter(),
                footer = HeroesLoaderStateAdapter()
            )
            val manager =
                GridLayoutManager(context, resources.getInteger(R.integer.grid_count))
            rvCharacter.layoutManager = manager
            rvCharacter.addItemDecoration(
                if (orientationLand)
                    LinearSpacingItemDecoration(context.dpToIntPx(8))
                else
                    GridSpacingItemDecoration(context.dpToIntPx(8))
            )
            adapter.addLoadStateListener { state: CombinedLoadStates ->
                rvCharacter.isVisible = state.refresh != LoadState.Loading
                pbProgress.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.viewModelScope.launch {
            viewModel.heroes.collectLatest {
                adapter.submitData(lifecycle, it)
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