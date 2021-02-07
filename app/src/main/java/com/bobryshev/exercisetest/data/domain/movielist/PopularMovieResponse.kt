package com.bobryshev.exercisetest.data.domain.movielist

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("popularity")
    val popularity: String,
)

fun PopularMovieResponse.MovieListItem(): MovieListItem {
    val item = this
    return MovieListItem.build {
        id = item.id
        posterPath = item.posterPath
        title = item.title
        popularity = item.popularity
    }
}

