package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SliderWithLabel(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    finiteEnd: Boolean,
    labelMinWidth: Dp = 24.dp,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: SliderColors = SliderDefaults.colors()
) {

    Column {

        BoxWithConstraints(
            modifier = modifier.fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 8.dp // Since we use a padding of 4.dp on either sides of the SliderLabel, we need to account for this in our calculation
            )

            val endValueText = if (!finiteEnd && value >= valueRange.endInclusive) "${
                value.toInt()
            }+" else value.toInt().toString()


            SliderLabel(
                label = valueRange.start.toInt().toString(),
                minWidth = labelMinWidth
            )

            if (value > valueRange.start) {
                SliderLabel(
                    label = endValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier.padding(start = offset)
                )
            }
        }

        Slider(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            colors = colors,
            steps = steps,
            interactionSource = interactionSource,
            onValueChangeFinished = onValueChangeFinished
        )

    }
}


@Composable
fun SliderLabel(
    label: String,
    minWidth: Dp,
    modifier: Modifier = Modifier
) {
    Text(
        label,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
            .defaultMinSize(minWidth = minWidth)
    )
}


private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {

    val coerced = value.coerceIn(
        valueRange.start,
        valueRange.endInclusive
    )
    val positionFraction = calcFraction(
        valueRange.start,
        valueRange.endInclusive,
        coerced
    )

    return (boxWidth - labelWidth) * positionFraction
}


// Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
private fun calcFraction(
    a: Float,
    b: Float,
    pos: Float
) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(
        0f,
        1f
    )