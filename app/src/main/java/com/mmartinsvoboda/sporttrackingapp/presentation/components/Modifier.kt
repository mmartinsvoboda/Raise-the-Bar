package com.mmartinsvoboda.sporttrackingapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.leftRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black),
    enabled: Boolean = true
): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    if (enabled) {
                        drawLine(
                            brush = brush,
                            strokeWidth = width.toPx(),
                            start = Offset(
                                0f,
                                0f
                            ),
                            end = Offset(
                                0f,
                                size.height
                            )
                        )
                    }
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = RectangleShape
    }
)

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }