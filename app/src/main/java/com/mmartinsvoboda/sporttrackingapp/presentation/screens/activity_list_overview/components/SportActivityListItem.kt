package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.SpeakerNotes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.presentation.components.IconAndTextRow
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerSmall
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerTiny
import com.mmartinsvoboda.sporttrackingapp.presentation.components.leftRectBorder
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun SportActivityListItem(
    sportActivity: SportActivity, modifier: Modifier, onClick: () -> Unit
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .leftRectBorder(
            width = SportTrackingAppTheme.paddings.smallPadding,
            brush = SolidColor(
                when (sportActivity.enjoyment) {
                    in 0..3 -> Color.Red
                    in 4..7 -> SportTrackingAppTheme.colors.secondary
                    else -> Color.Green
                }
            ),
            enabled = true
        )
        .clickable {
            onClick()
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SportTrackingAppTheme.paddings.defaultPadding)
        ) {
            Text(
                text = sportActivity.sport._name,
                style = SportTrackingAppTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            SpacerTiny()

            Text(
                text = "${sportActivity.performance} ${sportActivity.sport.sportUnit.unit}",
                style = SportTrackingAppTheme.typography.h6,
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

            SpacerSmall()

            IconAndTextRow(
                text = "${
                    sportActivity.startDateTime.format(
                        DateTimeFormatter.ofLocalizedDateTime(
                            FormatStyle.MEDIUM
                        )
                    )
                }\n${
                    sportActivity.endDateTime.format(
                        DateTimeFormatter.ofLocalizedDateTime(
                            FormatStyle.MEDIUM
                        )
                    )
                }",
                icon = Icons.Outlined.Schedule
            )

            SpacerSmall()

            IconAndTextRow(
                text = sportActivity.place, icon = Icons.Outlined.Place
            )

            SpacerSmall()

            IconAndTextRow(
                text = sportActivity.description,
                icon = Icons.Outlined.SpeakerNotes,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}