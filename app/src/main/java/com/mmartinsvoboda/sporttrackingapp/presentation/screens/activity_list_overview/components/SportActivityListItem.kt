package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.presentation.components.*
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun SportActivityListItem(
    sportActivity: SportActivity, modifier: Modifier, onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .leftRectBorder(
                width = SportTrackingAppTheme.paddings.smallPadding,
                brush = SolidColor(SportTrackingAppTheme.colors.primary),
                enabled = sportActivity.isBackedUp
            )
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SportTrackingAppTheme.paddings.defaultPadding)
        ) {
            Text(
                text = sportActivity.name,
                style = SportTrackingAppTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            SpacerSmall()

            IconAndTextRow(
                text = "${sportActivity.start} - ${sportActivity.end}",
                icon = Icons.Outlined.Schedule
            )

            SpacerTiny()

            IconAndTextRow(text = sportActivity.place, icon = Icons.Outlined.Place)
        }
    }
}


@Preview
@Composable
private fun SportActivityListItemPreview() {
    Column {
        SportActivityListItem(
            sportActivity = SportActivity(
                end = "12:00",
                start = "11:00",
                isBackedUp = true,
                place = "Brno",
                id = 1,
                name = "Běh"
            ), modifier = Modifier
        ) {

        }

        SpacerDefault()

        SportActivityListItem(
            sportActivity = SportActivity(
                end = "12:00",
                start = "11:00",
                isBackedUp = false,
                place = "Krkonoše",
                id = 2,
                name = "Chůze do opravdu velkého kopce"
            ), modifier = Modifier
        ) {

        }
    }
}