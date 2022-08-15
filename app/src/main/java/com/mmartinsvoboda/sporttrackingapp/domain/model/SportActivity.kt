package com.mmartinsvoboda.sporttrackingapp.domain.model

import java.time.LocalDateTime

data class SportActivity(
    val endDateTime: LocalDateTime,
    val id: Int,
    val isBackedUp: Boolean,
    val name: String,
    val description: String,
    val enjoyment: Int,
    val place: String,
    val startDateTime: LocalDateTime
)
