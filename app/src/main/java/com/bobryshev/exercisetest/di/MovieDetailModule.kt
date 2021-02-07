package com.bobryshev.exercisetest.di

import com.bobryshev.exercisetest.data.network.datasource.detailmovie.IMovieDetailDataSource
import com.bobryshev.exercisetest.data.network.datasource.detailmovie.MovieDetailDataSource
import com.bobryshev.exercisetest.data.repository.detailmovie.IDetailMovieRepository
import com.bobryshev.exercisetest.data.repository.detailmovie.DetailMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class MovieDetailModule {

    @ViewModelScoped
    @Binds
    abstract fun bindImdbMovieDetailDataSource(movieDetailDataSource : MovieDetailDataSource): IMovieDetailDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindImdbMovieDetailRepository(movieDetailRepository : DetailMovieRepository): IDetailMovieRepository
}