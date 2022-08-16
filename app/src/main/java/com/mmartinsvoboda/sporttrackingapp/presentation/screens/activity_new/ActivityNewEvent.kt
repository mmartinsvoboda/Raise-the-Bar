package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import java.time.LocalDateTime

sealed class ActivityNewEvent {
    object AddNewEvent : ActivityNewEvent()
    data class SetSportType(val sportType: SportType) : ActivityNewEvent()
    data class SetPerformance(val performance: String) : ActivityNewEvent()
    data class SetDescription(val description: String) : ActivityNewEvent()
    data class SetLocation(val location: String) : ActivityNewEvent()
    data class SetStartDateTime(val localDateTime: LocalDateTime) : ActivityNewEvent()
    data class SetEndDateTime(val localDateTime: LocalDateTime) : ActivityNewEvent()
    data class SetEnjoyment(val enjoyment: Int) : ActivityNewEvent()
}
