package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.components.date_time_picker.DateTimePicker
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel

@Composable
fun DateTimeSection(state: ActivityNewState, model: ActivityNewViewModel) {
    DateTimePicker(
        currentLocalDateTime = state.startDateTime,
        label = "Start",
        modifier = Modifier.fillMaxWidth(),
        isError = !state.startDateTimeError.isNullOrBlank()
    ) {
        model.onEvent(
            ActivityNewEvent.SetStartDateTime(it)
        )
    }

    DisplayErrorTextOnError(state.startDateTimeError)

    SpacerDefault()

    DateTimePicker(
        currentLocalDateTime = state.endDateTime,
        label = "End",
        modifier = Modifier.fillMaxWidth(),
        isError = !state.endDateTimeError.isNullOrBlank()
    ) {
        model.onEvent(
            ActivityNewEvent.SetEndDateTime(it)
        )
    }

    DisplayErrorTextOnError(state.endDateTimeError)
}