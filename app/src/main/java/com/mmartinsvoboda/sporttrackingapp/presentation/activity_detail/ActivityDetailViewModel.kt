package com.mmartinsvoboda.sporttrackingapp.presentation.activity_detail

import androidx.lifecycle.SavedStateHandle
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
class ActivityDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    sportActivityRepository: SportActivityRepository
) : ViewModel() {

    private val _listOfActivities = MutableStateFlow<SportActivity?>(null)
    val listOfActivities: StateFlow<SportActivity?> = _listOfActivities

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("id")?.let { id ->
                sportActivityRepository.getSportActivity(id, false).collect {
                    _listOfActivities.value = it.data
                }
            }
        }
    }
}