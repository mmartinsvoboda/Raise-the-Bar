package com.mmartinsvoboda.sporttrackingapp.di

import com.mmartinsvoboda.sporttrackingapp.common.Constants
import com.mmartinsvoboda.sporttrackingapp.data.remote.SportActivityApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSportActivityApi(): SportActivityApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportActivityApi::class.java)
    }
}