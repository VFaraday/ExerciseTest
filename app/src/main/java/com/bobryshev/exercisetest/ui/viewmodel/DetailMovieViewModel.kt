package com.bobryshev.exercisetest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.data.network.Result
import com.bobryshev.exercisetest.data.repository.detailmovie.IDetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val repository: IDetailMovieRepository) : ViewModel() {

    private val _detailMovieLiveData : MutableLiveData<MovieDetail> = MutableLiveData()
    val detailMovieLiveData : LiveData<MovieDetail>
        get() = _detailMovieLiveData

    private val _errorLiveData : MutableLiveData<String> = MutableLiveData()
    val errorLiveData : LiveData<String>
        get() = _errorLiveData

    fun getMovieDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDetail(id)
                .collect { result ->
                    when(result) {
                        is Result.Success -> _detailMovieLiveData.postValue(result.data)
                        is Result.Error -> _errorLiveData.postValue(result.exception?.message)
                    }
                }
        }

    }
}