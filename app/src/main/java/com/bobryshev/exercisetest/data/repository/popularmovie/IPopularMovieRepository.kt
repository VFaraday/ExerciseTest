package com.bobryshev.exercisetest.data.repository.popularmovie

import androidx.paging.PagingData
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import kotlinx.coroutines.flow.Flow

interface IPopularMovieRepository {

    fun loadAllMovies(): Flow<PagingData<MovieListItem>>
}