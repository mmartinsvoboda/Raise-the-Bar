package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity

sealed class ActivityListEvent {
    data class LoadActivityList(val refresh: Boolean) : ActivityListEvent()
    data class ActivityDelete(val sportActivity: SportActivity) : ActivityListEvent()
    data class ActivitySyncOn(val sportActivity: SportActivity) : ActivityListEvent()
    data class ActivitySyncOff(val sportActivity: SportActivity) : ActivityListEvent()
    data class UserLogOut(val onSuccessfulLogOut: () -> Unit) : ActivityListEvent()
}
