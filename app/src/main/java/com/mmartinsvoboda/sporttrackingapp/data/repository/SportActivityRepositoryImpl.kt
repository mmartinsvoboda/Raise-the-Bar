package com.mmartinsvoboda.sporttrackingapp.data.repository

import com.mmartinsvoboda.sporttrackingapp.common.data.BaseDataSource
import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.common.data.performGetOperation
import com.mmartinsvoboda.sporttrackingapp.common.data.syncerForEntity
import com.mmartinsvoboda.sporttrackingapp.data.local.SportActivityDatabase
import com.mmartinsvoboda.sporttrackingapp.data.remote.SportActivityApi
import com.mmartinsvoboda.sporttrackingapp.data.toSportActivity
import com.mmartinsvoboda.sporttrackingapp.data.toSportActivityEntity
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportActivityRepositoryImpl @Inject constructor(
    val api: SportActivityApi,
    val db: SportActivityDatabase
) : SportActivityRepository, BaseDataSource() {
    override suspend fun getSportActivity(
        id: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>> =
        performGetOperation(
            fetchFromLocal = {
                db.sportActivityDao.getSportActivityFlow(id)
            },
            fetchFromRemote = {
                getResult("getSportActivity") {
                    api.getActivity("", id)
                }
            },
            processRemoteData = { response ->
                response.items.firstOrNull()?.toSportActivityEntity()
            },
            syncRemoteData = { local, remote ->
                syncerForEntity(db.sportActivityDao) { it.id }
                    .sync(local, remote)
            },
            processFinalData = { data ->
                data?.toSportActivity()
            }
        )

    val user: String = ""
    override suspend fun getAllSportActivities(fetchFromRemote: Boolean): Flow<Resource<List<SportActivity>>> =
        performGetOperation(
            fetchFromLocal = {
                db.sportActivityDao.getSportActivityListFlow(user)
            },
            fetchFromRemote = {
                getResult("getAllSportActivities") {
                    api.getActivities(user)
                }
            },
            processRemoteData = { response ->
                response.items.map { it.toSportActivityEntity() }
            },
            syncRemoteData = { local, remote ->
                syncerForEntity(db.sportActivityDao) { it.id }
                    .sync(local, remote)
            },
            processFinalData = { data ->
                data.map { it.toSportActivity() }
            }
        )

    override suspend fun deleteSportActivity() {
        TODO("Not yet implemented")
    }

    override suspend fun addSportActivity() {
        TODO("Not yet implemented")
    }
}