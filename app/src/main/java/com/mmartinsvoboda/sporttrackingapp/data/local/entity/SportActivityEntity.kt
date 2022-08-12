package com.mmartinsvoboda.sporttrackingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SportActivityEntity(
    val end: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isBackedUp: Boolean,
    val name: String,
    val place: String,
    val remoteId: String?,
    val start: String,
    val user: String
)
