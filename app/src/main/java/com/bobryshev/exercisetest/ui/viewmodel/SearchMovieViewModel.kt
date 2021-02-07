package com.bobryshev.exercisetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import com.bobryshev.exercisetest.data.repository.searchmovie.ISearchMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val searchMovieRepository: ISearchMovieRepository) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<MovieListItem>>? = null

    fun searchMovie(queryString: String) : Flow<PagingData<MovieListItem>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<MovieListItem>> = searchMovieRepository.searchMovies(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}