package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.SpeakerNotes
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.IconAndTextRow
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerSmall
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun ActivityDetailScreenDetailsCard(sportActivity: SportActivity) {
    CardSportApp(
        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SportTrackingAppTheme.paddings.defaultPadding)
        ) {
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
                }", icon = Icons.Outlined.Schedule
            )

            SpacerSmall()

            IconAndTextRow(
                text = sportActivity.place, icon = Icons.Outlined.Place
            )

            SpacerSmall()

            IconAndTextRow(
                text = sportActivity.description,
                icon = Icons.Outlined.SpeakerNotes
            )

            SpacerSmall()

            IconAndTextRow(
                text = sportActivity.enjoyment.toString() + "/10",
                icon = Icons.Outlined.Star
            )
        }
    }
}