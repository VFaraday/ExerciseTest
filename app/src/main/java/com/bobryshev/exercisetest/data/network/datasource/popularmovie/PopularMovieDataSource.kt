package com.bobryshev.exercisetest.data.network.datasource.popularmovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bobryshev.exercisetest.Util
import com.bobryshev.exercisetest.data.domain.movielist.PopularMovieResponse
import com.bobryshev.exercisetest.data.network.ImdbApi
import retrofit2.HttpException
import java.io.IOException

class PopularMovieDataSource (private val api: ImdbApi) : PagingSource<Int, PopularMovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovieResponse>): Int? =  state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieResponse> {
        val page = params.key ?: Util.IMDB_STARTING_PAGE_INDEX
        return try {
            val response = api.getMovies(page)
            val players = response.results
            LoadResult.Page(
                data = players.orEmpty(),
                prevKey = if (page == Util.IMDB_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.page == response.totalPages) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}