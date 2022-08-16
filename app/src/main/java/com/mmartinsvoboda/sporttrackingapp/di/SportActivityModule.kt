package com.mmartinsvoboda.sporttrackingapp.di

import com.mmartinsvoboda.sporttrackingapp.data.local.SportActivityDatabase
import com.mmartinsvoboda.sporttrackingapp.data.remote.SportActivityApi
import com.mmartinsvoboda.sporttrackingapp.data.repository.SportActivityRepositoryImpl
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SportActivityModule {

    @Provides
    @Singleton
    fun provideSportActivityApi(
        api: SportActivityApi,
        db: SportActivityDatabase
    ): SportActivityRepository {
        return SportActivityRepositoryImpl(
            api,
            db
        )
    }

}