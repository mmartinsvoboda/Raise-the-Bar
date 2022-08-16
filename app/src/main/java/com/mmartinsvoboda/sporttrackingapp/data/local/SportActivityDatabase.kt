package com.mmartinsvoboda.sporttrackingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmartinsvoboda.sporttrackingapp.data.local.dao.SportActivityDao
import com.mmartinsvoboda.sporttrackingapp.data.local.entity.SportActivityEntity

@Database(
    entities = [SportActivityEntity::class],
    version = 1
)
abstract class SportActivityDatabase : RoomDatabase() {
    abstract val sportActivityDao: SportActivityDao
}