package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import java.time.LocalDateTime

data class ActivityNewState(
    val sport: SportType? = SportType.OutdoorRunning,
    val sportError: String? = null,
    val performance: String = "",
    val performanceError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val location: String = "",
    val locationError: String? = null,
    val startDateTime: LocalDateTime? = null,
    val startDateTimeError: String? = null,
    val endDateTime: LocalDateTime? = null,
    val endDateTimeError: String? = null,
    val enjoyment: Int = 5,
    val enjoymentError: String? = null,
    val syncToServer: Boolean = true,
    val isActionInProgress: Boolean = false
)
