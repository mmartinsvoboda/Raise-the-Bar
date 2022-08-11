package com.mmartinsvoboda.sporttrackingapp.presentation.activity_list_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityListOverviewViewModel @Inject constructor(
    sportActivityRepository: SportActivityRepository
) : ViewModel() {

    private val _listOfActivities = MutableStateFlow<List<SportActivity>?>(emptyList())
    val listOfActivities: StateFlow<List<SportActivity>?> = _listOfActivities

    init {
        viewModelScope.launch {
            sportActivityRepository.getAllSportActivities(false).collect {
                _listOfActivities.value = it.data
            }
        }
    }
}