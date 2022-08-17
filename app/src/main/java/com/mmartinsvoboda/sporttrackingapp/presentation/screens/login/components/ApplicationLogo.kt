package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.R
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun ApplicationLogo() {
    val maxHeight = (LocalConfiguration.current.screenHeightDp * 0.3f).dp

    val maxWidth =
        if (LocalConfiguration.current.screenWidthDp.dp > 600.dp) LocalConfiguration.current.screenWidthDp.dp * 0.5f else 600.dp * 0.5f

    Image(
        painter = painterResource(id = R.drawable.activity),
        contentDescription = "Application logo",
        modifier = Modifier
            .padding(bottom = SportTrackingAppTheme.paddings.largePadding)
            .heightIn(max = maxHeight)
            .widthIn(max = maxWidth)
            .fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}