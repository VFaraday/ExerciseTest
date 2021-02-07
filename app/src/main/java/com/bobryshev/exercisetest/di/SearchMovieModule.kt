package com.bobryshev.exercisetest.di

import com.bobryshev.exercisetest.data.repository.searchmovie.ISearchMovieRepository
import com.bobryshev.exercisetest.data.repository.searchmovie.SearchMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class SearchMovieModule {

    @ViewModelScoped
    @Binds
    abstract fun bindSearchMovieRepository(searchMovieRepository: SearchMovieRepository) : ISearchMovieRepository
}