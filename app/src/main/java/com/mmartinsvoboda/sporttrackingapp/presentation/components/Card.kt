package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun CardSportApp(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = SportTrackingAppTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        content = content
    )
}