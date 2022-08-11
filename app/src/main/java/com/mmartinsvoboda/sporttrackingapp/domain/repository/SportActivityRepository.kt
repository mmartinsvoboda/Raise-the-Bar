package com.mmartinsvoboda.sporttrackingapp.domain.repository

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import kotlinx.coroutines.flow.Flow

interface SportActivityRepository {

    suspend fun getSportActivity(
        id: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>>

    suspend fun getAllSportActivities(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<SportActivity>>>

    suspend fun deleteSportActivity()
    suspend fun addSportActivity()

}