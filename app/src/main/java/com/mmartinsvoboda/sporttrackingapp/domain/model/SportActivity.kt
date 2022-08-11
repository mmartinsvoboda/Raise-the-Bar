package com.mmartinsvoboda.sporttrackingapp.domain.model

data class SportActivity(
    val end: String,
    val id: String,
    val isBackedUp: Boolean,
    val name: String,
    val place: String,
    val start: String,
    val user: String
)
