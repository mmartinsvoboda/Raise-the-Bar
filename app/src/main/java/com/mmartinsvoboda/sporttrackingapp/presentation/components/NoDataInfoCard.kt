package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun NoDataInfoCard(
    title: String?,
    description: String?,
    image: Painter,
    contentDescription: String?,
    modifier: Modifier
) {
    if (title.isNullOrBlank()) {
        CardSportApp(
            modifier = modifier
        ) {
            NoDataInfo(
                title = description,
                image = image,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SportTrackingAppTheme.paddings.defaultPadding)
            )
        }
    } else {
        CardSportAppWithTitle(
            title = title,
            modifier = modifier
        ) {
            NoDataInfo(
                title = description,
                image = image,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SportTrackingAppTheme.paddings.defaultPadding)
            )
        }
    }
}

@Composable
fun NoDataInfo(
    title: String?,
    image: Painter,
    contentDescription: String?,
    modifier: Modifier
) {
    Column(
        modifier
    ) {
        title?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth()
            )
            SpacerDefault()
        }

        Image(
            painter = image,
            contentDescription = contentDescription ?: title,
            modifier = Modifier
                .heightIn(max = 200.dp)
                .widthIn(max = 200.dp)
                .fillMaxWidth(0.75f)
                .align(Alignment.CenterHorizontally)
        )
    }
}