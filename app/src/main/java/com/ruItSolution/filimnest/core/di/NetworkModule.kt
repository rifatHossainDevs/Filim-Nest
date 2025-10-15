package com.ruItSolution.filimnest.core.di

import com.ruItSolution.filimnest.core.utils.Constants
import com.ruItSolution.filimnest.data.remote.MovieApiService
import com.ruItSolution.filimnest.data.repoImpl.MovieApiRepositoryImpl
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    // Movie
    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApiRepository(movieApiService: MovieApiService): MovieApiRepository {
        return MovieApiRepositoryImpl(movieApiService)
    }

}