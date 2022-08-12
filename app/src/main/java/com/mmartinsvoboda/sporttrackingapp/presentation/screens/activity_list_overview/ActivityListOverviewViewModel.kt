package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import androidx.lifecycle.ViewModel
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ActivityListOverviewViewModel @Inject constructor(
    sportActivityRepository: SportActivityRepository
) : ViewModel() {

    private val _listOfActivities = MutableStateFlow<List<SportActivity>?>(emptyList())
    val listOfActivities: StateFlow<List<SportActivity>?> = _listOfActivities

    init {
    }
}