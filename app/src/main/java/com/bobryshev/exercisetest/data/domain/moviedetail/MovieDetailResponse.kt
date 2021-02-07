package com.bobryshev.exercisetest.data.domain.moviedetail

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("popularity")
    val popularity: String,
)

fun MovieDetailResponse.toMovieDetail(): MovieDetail {
    val item = this
    return MovieDetail.build {
        id = item.id
        posterPath = item.posterPath
        title = item.title
        popularity = item.popularity
    }
}