package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun SpacerTiny() {
    Spacer(modifier = Modifier.size(SportTrackingAppTheme.paddings.tinyPadding))
}

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.size(SportTrackingAppTheme.paddings.smallPadding))
}

@Composable
fun SpacerDefault() {
    Spacer(modifier = Modifier.size(SportTrackingAppTheme.paddings.defaultPadding))
}

@Composable
fun SpacerLarge() {
    Spacer(modifier = Modifier.size(SportTrackingAppTheme.paddings.largePadding))
}