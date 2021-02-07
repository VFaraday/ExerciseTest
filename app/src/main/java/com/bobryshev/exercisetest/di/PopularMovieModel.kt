package com.bobryshev.exercisetest.di

import com.bobryshev.exercisetest.data.repository.popularmovie.IPopularMovieRepository
import com.bobryshev.exercisetest.data.repository.popularmovie.PopularMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class PopularMovieModel {

    @ViewModelScoped
    @Binds
    abstract fun bindImdbPopularMovieRepository(popularMovieRepository: PopularMovieRepository): IPopularMovieRepository
}