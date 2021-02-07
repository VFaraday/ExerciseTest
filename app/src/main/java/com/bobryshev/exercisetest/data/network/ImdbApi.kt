package com.bobryshev.exercisetest.data.network

import com.bobryshev.exercisetest.data.domain.movielist.PopularMovieListResponse
import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImdbApi {

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int) : PopularMovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: String) : MovieDetailResponse

    @GET("search/movie?include_adult=false")
    suspend fun searchMovie(
        @Query("query") searchText: String,
        @Query("page") page: Int
    ) : PopularMovieListResponse
}