package com.bobryshev.exercisetest.data.domain.moviedetail

data class MovieDetail(
    val id: String,
    val posterPath: String?,
    val title: String,
    val popularity: String,
) {

    private constructor(builder: Builder): this(builder.id, builder.posterPath, builder.title, builder.popularity)

    class Builder {
        var id: String = ""
        var posterPath: String? = null
        var title: String = ""
        var popularity: String = ""

        fun build() =  MovieDetail(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}