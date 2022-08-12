package com.mmartinsvoboda.sporttrackingapp.data.repository

import com.mmartinsvoboda.sporttrackingapp.common.data.BaseDataSource
import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.common.data.performGetOperation
import com.mmartinsvoboda.sporttrackingapp.common.data.syncerForEntity
import com.mmartinsvoboda.sporttrackingapp.common.toGsonString
import com.mmartinsvoboda.sporttrackingapp.data.local.SportActivityDatabase
import com.mmartinsvoboda.sporttrackingapp.data.remote.SportActivityApi
import com.mmartinsvoboda.sporttrackingapp.data.toSportActivity
import com.mmartinsvoboda.sporttrackingapp.data.toSportActivityEntity
import com.mmartinsvoboda.sporttrackingapp.data.toSportActivityItem
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportActivityRepositoryImpl @Inject constructor(
    private val api: SportActivityApi,
    private val db: SportActivityDatabase
) : SportActivityRepository, BaseDataSource() {
    override fun getSportActivity(
        id: Int,
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>> =
        performGetOperation(
            fetchFromLocal = {
                db.sportActivityDao.getSportActivityFlow(id)
            },
            shouldFetchFromRemote = { local ->
                fetchFromRemote || local == null
            },
            fetchFromRemote = {
                db.sportActivityDao.getSportActivityFlow(id).first()?.remoteId?.let {
                    getResult("getSportActivity") {
                        api.getActivity(user, it)
                    }
                } ?: Resource.success(null)
            },
            processRemoteData = { response ->
                response?.items?.firstOrNull()?.toSportActivityEntity()
            },
            syncRemoteData = { local, remote ->
                syncerForEntity(db.sportActivityDao) { it.id }
                    .sync(
                        local?.takeIf { !it.remoteId.isNullOrBlank() },
                        remote
                    )
            },
            processFinalData = { data ->
                data?.toSportActivity()
            }
        )

    override fun getAllSportActivities(
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<SportActivity>>> =
        performGetOperation(
            fetchFromLocal = {
                db.sportActivityDao.getSportActivityListFlow("test")
            },
            shouldFetchFromRemote = {
                fetchFromRemote || it.isEmpty()
            },
            fetchFromRemote = {
                getResult("getAllSportActivities") {
                    api.getActivities("test")
                }
            },
            processRemoteData = { response ->
                response.items.map { it.toSportActivityEntity() }
            },
            syncRemoteData = { local, remote ->
                syncerForEntity(db.sportActivityDao) { it.id }
                    .sync(
                        local.filter { !it.remoteId.isNullOrBlank() },
                        remote
                    )
            },
            processFinalData = { data ->
                data.map { it.toSportActivity() }
            }
        )

    override suspend fun deleteSportActivityFromLocal(
        sportActivity: SportActivity
    ) {
        val sportActivityEntity = db.sportActivityDao.getSportActivityFlow(sportActivity.id).first()
        sportActivityEntity?.let { db.sportActivityDao.deleteEntity(it) }
    }

    override suspend fun addSportActivityToLocal(
        sportActivity: SportActivity
    ) {
        db.sportActivityDao.insert(sportActivity.toSportActivityEntity())
    }

    override suspend fun deleteSportActivityFromRemote(
        id: Int,
        user: String
    ) {
        val sportActivityEntity = db.sportActivityDao.getSportActivityFlow(id).first()

        if (sportActivityEntity != null && sportActivityEntity.isBackedUp && !sportActivityEntity.remoteId.isNullOrBlank()) {
            api.deleteActivity(user, sportActivityEntity.remoteId)

            val newLocalSportActivityEntity = sportActivityEntity.copy(
                isBackedUp = false,
                remoteId = null
            )

            db.sportActivityDao.update(newLocalSportActivityEntity)
        }
    }

    override suspend fun addSportActivityToRemote(
        user: String,
        sportActivity: SportActivity
    ): Boolean {
        val sportActivityEntity = db.sportActivityDao.getSportActivityFlow(sportActivity.id).first()

        if (sportActivityEntity != null && !sportActivityEntity.isBackedUp) {
            val response = api.addActivity(user, sportActivity.toSportActivityItem().toGsonString())

            if (response.isSuccessful && response.body() != null) {
                val newLocalSportActivityEntity = sportActivityEntity.copy(
                    isBackedUp = true,
                    remoteId = response.body()!!.id
                )

                db.sportActivityDao.update(newLocalSportActivityEntity)
            }

            return true
        }

        return false
    }


}