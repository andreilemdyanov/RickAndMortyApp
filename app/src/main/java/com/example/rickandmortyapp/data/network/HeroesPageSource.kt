package com.example.rickandmortyapp.data.network

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.model.Heroes
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.model.toHeroes
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class HeroesPageSource(private val heroesApi: HeroesApi) : RxPagingSource<Int, Hero>() {

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Hero>> {
        val page: Int = params.key ?: 1
        return heroesApi.fetchResultsRx(page)
            .subscribeOn(Schedulers.io())
            .map { it.toHeroes() }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(heroes: Heroes, page: Int): LoadResult<Int, Hero> {
        return LoadResult.Page(
            data = heroes.list,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == heroes.pagesCount) null else page + 1
        )
    }
}