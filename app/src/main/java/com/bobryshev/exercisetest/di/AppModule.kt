package com.bobryshev.exercisetest.di

import com.bobryshev.exercisetest.BuildConfig
import com.bobryshev.exercisetest.data.network.ImdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideApiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.APP_KEY_1)
                .build()

        val requestBuilder = original.newBuilder()
                .url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: Interceptor) = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            })
            .addInterceptor(apiKeyInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideImdbApi(retrofit: Retrofit) = retrofit.create(ImdbApi::class.java)
}