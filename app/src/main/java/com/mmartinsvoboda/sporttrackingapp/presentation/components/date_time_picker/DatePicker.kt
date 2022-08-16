package com.mmartinsvoboda.sporttrackingapp.presentation.components.date_time_picker

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.util.*

@Composable
fun getDatePicker(
    onDateSelected: (localDate: LocalDate) -> Unit
): DatePickerDialog {
    // Fetching the Local Context
    val context = LocalContext.current

    // Initializing a Calendar
    val calendar = Calendar.getInstance()

    // Fetching current year, month and day
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    return DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateSelected(
                LocalDate.of(
                    mYear,
                    mMonth + 1,
                    mDayOfMonth
                )
            )
        },
        year,
        month,
        day
    )
}