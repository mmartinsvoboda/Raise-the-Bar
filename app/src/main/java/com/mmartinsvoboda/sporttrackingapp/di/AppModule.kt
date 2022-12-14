package com.mmartinsvoboda.sporttrackingapp.di

import android.app.Application
import androidx.room.Room
import com.mmartinsvoboda.sporttrackingapp.common.Constants
import com.mmartinsvoboda.sporttrackingapp.data.local.SportActivityDatabase
import com.mmartinsvoboda.sporttrackingapp.data.manager.UserManagerImpl
import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.devel.ProtoSettingsRepo
import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.devel.ProtoSettingsRepoImpl
import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user.ProtoUserRepo
import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user.ProtoUserRepoImpl
import com.mmartinsvoboda.sporttrackingapp.data.remote.SportActivityApi
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
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
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(SportActivityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSportActivityDatabase(app: Application): SportActivityDatabase {
        return Room.databaseBuilder(
            app,
            SportActivityDatabase::class.java,
            "sportactivitydb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserManager(protoUserRepo: ProtoUserRepo): UserManager {
        return UserManagerImpl(protoUserRepo)
    }

    @Provides
    @Singleton
    fun provideProtoUserRepo(app: Application): ProtoUserRepo {
        return ProtoUserRepoImpl(app)
    }

    @Provides
    @Singleton
    fun provideProtoSettingsRepo(app: Application): ProtoSettingsRepo {
        return ProtoSettingsRepoImpl(app)
    }

}