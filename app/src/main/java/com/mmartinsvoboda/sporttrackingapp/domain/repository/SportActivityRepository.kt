package com.mmartinsvoboda.sporttrackingapp.domain.repository

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import kotlinx.coroutines.flow.Flow

interface SportActivityRepository {

    fun getSportActivity(
        id: Int,
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>>

    fun getAllSportActivities(
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<SportActivity>>>

    suspend fun deleteSportActivityFromLocal(sportActivity: SportActivity)
    suspend fun addSportActivityToLocal(sportActivity: SportActivity)

    suspend fun deleteSportActivityFromRemote(id: Int, user: String)
    suspend fun addSportActivityToRemote(user: String, sportActivity: SportActivity): Boolean
}