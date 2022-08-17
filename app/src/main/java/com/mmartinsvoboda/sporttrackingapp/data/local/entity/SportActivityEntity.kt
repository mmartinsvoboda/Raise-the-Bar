package com.mmartinsvoboda.sporttrackingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SportActivityEntity(
    val endDateTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isBackedUp: Boolean,
    val sport: String,
    val description: String,
    val enjoyment: Int,
    val place: String,
    val remoteId: String?,
    val startDateTime: Long,
    val user: String,
    val performance: String
)
