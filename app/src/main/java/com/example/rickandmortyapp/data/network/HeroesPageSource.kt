package com.example.rickandmortyapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.model.Hero
import com.example.rickandmortyapp.data.network.api.EpisodeApi
import com.example.rickandmortyapp.data.network.api.HeroesApi
import retrofit2.HttpException

class HeroesPageSource(private val heroesApi: HeroesApi, private val episodeApi: EpisodeApi) :
    PagingSource<Int, Hero>() {

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(20)

        val heroesResponse = heroesApi.fetchResults(page)

        return if (heroesResponse.isSuccessful) {
            val characters = checkNotNull(heroesResponse.body())
                .results
                .map { characterResponse ->
                    characterResponse.transform()
                }.map { hero ->
                    val episode = episodeApi.getEpisode(
                        hero.firstEpisode.substringAfterLast("/").toInt()
                    )
                    hero.copy(firstEpisode = episode.name)
                }

            val nextKey = if (characters.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(characters, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(heroesResponse))
        }

    }
}