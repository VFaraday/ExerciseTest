package com.bobryshev.exercisetest.data.domain.movielist

import com.google.gson.annotations.SerializedName

data class PopularMovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMovieResponse>?,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
)