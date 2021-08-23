package com.example.rickandmortyapp.data.network

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.model.Heroes
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import com.example.rickandmortyapp.data.network.model.toHeroes
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class HeroesPageSource(private val heroesApi: HeroesApi, private val episodeApi: EpisodeApi) :
    RxPagingSource<Int, Hero>() {

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Hero>> {
        val page: Int = params.key ?: 1

        val episodes = heroesApi.fetchResultsRx(page)
            .map { it.toHeroes() }
            .flatMap { Observable.fromIterable(it.list) }
            .flatMap {
                episodeApi.getEpisode(it.firstEpisode.substringAfterLast("/").toInt())
            }

        val heroes = heroesApi.fetchResultsRx(page)
            .map { it.toHeroes() }
            .flatMap { Observable.fromIterable(it.list) }

        val result = heroes.zipWith(episodes) { one, two -> one.copy(firstEpisode = two.name) }
            .subscribeOn(Schedulers.io())

        return Single.fromObservable(result.toList().toObservable()
            .zipWith(heroesApi.fetchResultsRx(page))
            { one, two -> Heroes(two.toHeroes().pagesCount, one) }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
            .subscribeOn(Schedulers.io()))
    }

    private fun toLoadResult(heroes: Heroes, page: Int): LoadResult<Int, Hero> {
        return LoadResult.Page(
            data = heroes.list,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == heroes.pagesCount) null else page + 1
        )
    }
}