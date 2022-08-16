package com.mmartinsvoboda.sporttrackingapp.presentation.components.date_time_picker

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun DateTimePicker(
    currentLocalDateTime: LocalDateTime,
    label: String,
    modifier: Modifier = Modifier,
    onChanged: (localDateTime: LocalDateTime) -> Unit
) {

    val localDateTime = rememberSaveable {
        mutableStateOf<LocalDateTime?>(null)
    }

    val timePicker = getTimePicker {
        localDateTime.value =
            (localDateTime.value ?: LocalDateTime.now()).withHour(it.hour)
                .withMinute(it.minute)

        onChanged(localDateTime.value ?: LocalDateTime.now())
    }

    val datePicker = getDatePicker {
        localDateTime.value =
            (localDateTime.value ?: LocalDateTime.now()).withYear(it.year)
                .withMonth(it.monthValue).withDayOfMonth(it.dayOfMonth)

        timePicker.show()
    }

    OutlinedTextField(value = currentLocalDateTime.format(
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
            .withZone(ZoneId.systemDefault())
    ),
        onValueChange = {},
        modifier = modifier,
        label = { Text(label) },
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            Icon(
                Icons.Outlined.CalendarMonth,
                "calendar"
            )
        },
        readOnly = true,
        singleLine = true,
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        // works like onClick
                        datePicker.show()
                    }
                }
            }
        })
}