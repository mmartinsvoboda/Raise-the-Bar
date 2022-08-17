package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerTiny
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun ActivityDetailScreenHeader(sportActivity: SportActivity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
    ) {
        Text(
            text = sportActivity.sport._name,
            style = SportTrackingAppTheme.typography.h3,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        SpacerTiny()

        Text(
            text = "${sportActivity.performance} ${sportActivity.sport.sportUnit.unit}",
            style = SportTrackingAppTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )

        SpacerTiny()

        Text(
            text = if (sportActivity.isBackedUp) "Synced" else "Not synced",
            style = SportTrackingAppTheme.typography.caption,
            color = if (sportActivity.isBackedUp) SportTrackingAppTheme.colors.primary else SportTrackingAppTheme.colors.error
        )
    }
}