package com.bobryshev.exercisetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import com.bobryshev.exercisetest.data.repository.popularmovie.IPopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val popularMovieRepository : IPopularMovieRepository) : ViewModel() {

    private var currentResult: Flow<PagingData<MovieListItem>>? = null

    fun getPopularMovieList(): Flow<PagingData<MovieListItem>> {
        val newResult = popularMovieRepository.loadAllMovies().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}