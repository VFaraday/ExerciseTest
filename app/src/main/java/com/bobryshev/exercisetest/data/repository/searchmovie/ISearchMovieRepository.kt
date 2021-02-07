package com.bobryshev.exercisetest.data.repository.searchmovie

import androidx.paging.PagingData
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import kotlinx.coroutines.flow.Flow

interface ISearchMovieRepository {

    fun searchMovies(queryText: String): Flow<PagingData<MovieListItem>>
}