package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity

data class ActivityListState(
    val isLoading: Boolean = false,
    val activities: List<SportActivity> = emptyList(),
    val error: String = String(),
    val isActionInProgress: Boolean = false
)
