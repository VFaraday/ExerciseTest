package com.bobryshev.exercisetest.data.repository.detailmovie

import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.data.network.Result
import com.bobryshev.exercisetest.data.network.datasource.detailmovie.IMovieDetailDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieRepository @Inject constructor(private val dataSource: IMovieDetailDataSource) : IDetailMovieRepository {

    override suspend fun getMovieDetail(id: String): Flow<Result<MovieDetail>> = dataSource.getMovieDetail(id)
}