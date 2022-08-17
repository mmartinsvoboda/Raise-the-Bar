package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SliderWithLabel
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerSmall
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import kotlin.math.roundToInt

@Composable
fun EnjoymentSection(state: ActivityNewState, model: ActivityNewViewModel) {
    val color = animateColorAsState(
        targetValue = when (state.enjoyment) {
            in 0..3 -> Color.Red
            in 4..7 -> SportTrackingAppTheme.colors.secondary
            else -> Color.Green
        }
    )

    Text(text = "Enjoyment")

    SpacerSmall()

    SliderWithLabel(
        value = state.enjoyment.toFloat(),
        onValueChange = { model.onEvent(ActivityNewEvent.SetEnjoyment(it.roundToInt())) },
        steps = 1,
        valueRange = 0f..10f,
        colors = SliderDefaults.colors(
            activeTrackColor = color.value, thumbColor = color.value
        ),
        modifier = Modifier.fillMaxWidth(),
        finiteEnd = true
    )

    DisplayErrorTextOnError(state.enjoymentError)
}