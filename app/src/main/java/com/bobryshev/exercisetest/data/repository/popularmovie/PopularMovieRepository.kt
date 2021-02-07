package com.bobryshev.exercisetest.data.repository.popularmovie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bobryshev.exercisetest.Util
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import com.bobryshev.exercisetest.data.network.ImdbApi
import com.bobryshev.exercisetest.data.network.datasource.popularmovie.PopularMovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(private val api: ImdbApi) : IPopularMovieRepository {

    override fun loadAllMovies(): Flow<PagingData<MovieListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Util.NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PopularMovieDataSource(api) }
        ).flow
            .map { pageData ->
                pageData.map { it.MovieListItem() }
            }
    }
}