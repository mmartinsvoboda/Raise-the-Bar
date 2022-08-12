package com.mmartinsvoboda.sporttrackingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mmartinsvoboda.sporttrackingapp.data.local.entity.SportActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SportActivityDao : BaseDao<SportActivityEntity>() {
    @Query("SELECT * FROM SportActivityEntity WHERE id = :id")
    abstract fun getSportActivityFlow(id: Int): Flow<SportActivityEntity?>

    @Query("SELECT * FROM SportActivityEntity WHERE user = :user")
    abstract fun getSportActivityListFlow(user: String): Flow<List<SportActivityEntity>>
}