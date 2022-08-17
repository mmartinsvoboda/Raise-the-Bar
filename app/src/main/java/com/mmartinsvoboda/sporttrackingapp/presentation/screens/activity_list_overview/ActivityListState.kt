package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import com.mmartinsvoboda.sporttrackingapp.R
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.model.WeeklyChallenge
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_list_filter.ActivityListFilter

data class ActivityListState(
    val isLoading: Boolean = true,
    val activities: List<SportActivity> = emptyList(),
    val filter: ActivityListFilter = ActivityListFilter.ALL,
    val weeklyChallengeList: List<WeeklyChallenge> = listOf(
        WeeklyChallenge(description = "Cupcake ipsum dolor sit amet candy caramels. Danish shortbread chocolate bar shortbread pastry donut danish wafer. Oat cake candy canes cotton candy muffin shortbread fruitcake croissant cake."),
        WeeklyChallenge(description = "Cupcake ipsum dolor sit amet marzipan brownie. Powder jujubes jelly I love gummi bears wafer donut I love lollipop. Halvah caramels candy chupa chups lollipop I love halvah. Wafer candy canes cookie danish I love gummies sugar plum gingerbread."),
        WeeklyChallenge(description = "Cupcake ipsum dolor sit amet. Bonbon I love sesame snaps tootsie roll pudding gummies I love. Topping jelly-o chocolate candy canes chocolate cake jelly cake. Sesame snaps tiramisu I love liquorice cotton candy apple pie cupcake."),
        WeeklyChallenge(description = "Cupcake ipsum dolor sit amet tiramisu soufflé. Gingerbread tootsie roll gummi bears I love jujubes ice cream I love sweet roll tart. Brownie toffee cupcake cotton candy cake jelly caramels.")
    ),
    val error: String = String(),
    val isActionInProgress: Boolean = false,
    val user: String? = null,
    val noDataImage: Int = listOf(
        R.drawable.american_football,
        R.drawable.basketball,
        R.drawable.jogging
    ).random()
)
