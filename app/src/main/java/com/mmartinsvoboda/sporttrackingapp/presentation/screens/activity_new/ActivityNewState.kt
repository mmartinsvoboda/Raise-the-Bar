package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

data class ActivityNewState(
    val user: String = "",
    val userError: String? = null,
    val isActionInProgress: Boolean = false
)
