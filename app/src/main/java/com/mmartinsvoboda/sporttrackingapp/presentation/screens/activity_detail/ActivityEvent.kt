package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity

sealed class ActivityEvent {
    data class LoadActivity(val refresh: Boolean) : ActivityEvent()
    data class ActivityDelete(
        val sportActivity: SportActivity,
        val onSuccess: () -> Unit
    ) : ActivityEvent()

    data class ActivitySyncOn(val sportActivity: SportActivity) : ActivityEvent()
    data class ActivitySyncOff(val sportActivity: SportActivity) : ActivityEvent()
}
