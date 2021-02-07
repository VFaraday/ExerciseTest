package com.bobryshev.exercisetest.data.network.datasource.detailmovie

import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.data.network.Result
import kotlinx.coroutines.flow.Flow

interface IMovieDetailDataSource {

    suspend fun getMovieDetail(id: String) : Flow<Result<MovieDetail>>
}