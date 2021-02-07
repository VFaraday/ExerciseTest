package com.bobryshev.exercisetest.data.repository.searchmovie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bobryshev.exercisetest.Util
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import com.bobryshev.exercisetest.data.network.ImdbApi
import com.bobryshev.exercisetest.data.network.datasource.searchmovies.SearchMoviesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(private val api: ImdbApi) : ISearchMovieRepository{

    override fun searchMovies(queryText: String): Flow<PagingData<MovieListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Util.NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { SearchMoviesDataSource(api, queryText) }
        ).flow
            .map { pageData ->
                pageData.map { it.MovieListItem() }
            }
    }
}