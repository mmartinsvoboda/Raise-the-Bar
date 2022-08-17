package com.mmartinsvoboda.sporttrackingapp.presentation.components.date_time_picker

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime
import java.util.*

@Composable
fun getTimePicker(
    onTimeSelected: (localTime: LocalTime) -> Unit
): TimePickerDialog {
// Fetching local context
    val context = LocalContext.current

    // Declaring and initializing a calendar
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    // Creating a TimePicker dialod
    return TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            onTimeSelected(
                LocalTime.of(
                    mHour,
                    mMinute
                )
            )
        },
        hour,
        minute,
        true
    )
}