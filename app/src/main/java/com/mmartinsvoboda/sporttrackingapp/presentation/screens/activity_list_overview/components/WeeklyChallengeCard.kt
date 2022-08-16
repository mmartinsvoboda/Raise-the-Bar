package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.mmartinsvoboda.sporttrackingapp.domain.model.WeeklyChallenge
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportAppWithTitle
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeeklyChallengeCard(
    weeklyChallengeList: List<WeeklyChallenge>
) {
    if (weeklyChallengeList.size > 1) {
        val pagerState = rememberPagerState(0)

        CardSportAppWithTitle(
            title = "Weekly challenge",
            modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding),
            border = BorderStroke(
                width = 1.dp,
                SportTrackingAppTheme.colors.primary
            )
        ) {
            Column(Modifier) {
                HorizontalPager(count = weeklyChallengeList.size,
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                    key = { page -> weeklyChallengeList[page].description }) { page ->
                    val text = weeklyChallengeList[page].description

                    Text(
                        text = text,
                        modifier = Modifier.padding(SportTrackingAppTheme.paddings.defaultPadding)
                    )
                }

                // Pager
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            bottom = SportTrackingAppTheme.paddings.smallPadding,
                        ),
                    activeColor = SportTrackingAppTheme.colors.primary
                )
            }
        }
    }
}