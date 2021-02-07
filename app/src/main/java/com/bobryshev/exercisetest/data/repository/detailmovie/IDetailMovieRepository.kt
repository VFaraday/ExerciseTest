package com.bobryshev.exercisetest.data.repository.detailmovie

import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.data.network.Result
import kotlinx.coroutines.flow.Flow

interface IDetailMovieRepository {

    suspend fun getMovieDetail(id: String) : Flow<Result<MovieDetail>>
}