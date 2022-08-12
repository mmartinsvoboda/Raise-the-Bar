package com.mmartinsvoboda.sporttrackingapp.domain.model

data class SportActivity(
    val end: String,
    val id: Int,
    val isBackedUp: Boolean,
    val name: String,
    val place: String,
    val start: String
)
