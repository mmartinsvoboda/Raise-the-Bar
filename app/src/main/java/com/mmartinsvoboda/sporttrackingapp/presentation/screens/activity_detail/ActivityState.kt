package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity

data class ActivityState(
    val isLoading: Boolean = false,
    val activity: SportActivity? = null,
    val error: String = String(),
    val isActionInProgress: Boolean = false
)
