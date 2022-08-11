package com.mmartinsvoboda.sporttrackingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SportActivityEntity(
    val end: String,
    @PrimaryKey
    val id: String,
    val isBackedUp: Boolean,
    val name: String,
    val place: String,
    val start: String,
    val user: String
)
