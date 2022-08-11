package com.mmartinsvoboda.sporttrackingapp.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Transaction
import androidx.room.Update

abstract class BaseDao<in E> {
    @Insert(onConflict = REPLACE)
    abstract suspend fun insert(entity: E): Long

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertAll(vararg entity: E)

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertAll(entities: List<E>)

    @Update
    abstract suspend fun update(entity: E)

    @Delete
    abstract suspend fun deleteEntity(entity: E): Int

    @Transaction
    open suspend fun withTransaction(tx: suspend () -> Unit) = tx()
}
