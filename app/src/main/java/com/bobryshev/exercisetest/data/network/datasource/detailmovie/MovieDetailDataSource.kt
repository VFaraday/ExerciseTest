package com.bobryshev.exercisetest.data.network.datasource.detailmovie

import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.data.network.ImdbApi
import com.bobryshev.exercisetest.data.network.Result
import com.bobryshev.exercisetest.data.domain.moviedetail.toMovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDetailDataSource @Inject constructor (private val api: ImdbApi) : IMovieDetailDataSource {

    override suspend fun getMovieDetail(id: String): Flow<Result<MovieDetail>> = flow {
        try {
            val data = api.getMovie(id)
            emit(Result.Success(data.toMovieDetail()))
        } catch (e: IOException) {
            emit(Result.Error(e))
        } catch (e: HttpException) {
            emit(Result.Error(e))
        }
    }
}