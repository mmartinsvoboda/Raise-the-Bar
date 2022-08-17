package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun PerformanceSection(state: ActivityNewState, model: ActivityNewViewModel) {
    OutlinedTextField(
        value = state.performance,
        onValueChange = { model.onEvent(ActivityNewEvent.SetPerformance(it)) },
        modifier = Modifier.fillMaxWidth(),
        label = { state.sport?.sportUnit?._name?.let { Text(text = it) } },
        trailingIcon = {
            state.sport?.sportUnit?.unit?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(end = SportTrackingAppTheme.paddings.tinyPadding)
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(
            autoCorrect = false, keyboardType = KeyboardType.Number
        ),
        isError = !state.performanceError.isNullOrBlank()
    )

    DisplayErrorTextOnError(state.performanceError)
}