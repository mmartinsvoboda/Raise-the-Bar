package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.ActivityNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityNewViewModel @Inject constructor(
    private val activityNewUseCase: ActivityNewUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ActivityNewState())
    val state: StateFlow<ActivityNewState> = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ActivityNewEvent) {
        when (event) {
            is ActivityNewEvent.AddNewEvent -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)

                    activityNewUseCase(
                        sportActivity = SportActivity(
                            name = state.value.sport?._name ?: "",
                            description = state.value.description,
                            enjoyment = state.value.enjoyment,
                            place = state.value.location,
                            startDateTime = state.value.startDateTime,
                            endDateTime = state.value.endDateTime,
                            id = 0,
                            isBackedUp = false
                        )
                    )

                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
            is ActivityNewEvent.SetSportType -> {
                _state.value = state.value.copy(
                    sport = event.sportType,
                    performance = ""
                )
            }
            is ActivityNewEvent.SetPerformance -> {
                _state.value = state.value.copy(performance = event.performance)
            }
            is ActivityNewEvent.SetDescription -> {
                _state.value = state.value.copy(description = event.description)
            }
            is ActivityNewEvent.SetLocation -> {
                _state.value = state.value.copy(location = event.location)
            }
            is ActivityNewEvent.SetStartDateTime -> {
                _state.value = state.value.copy(
                    startDateTime = event.localDateTime
                )
            }
            is ActivityNewEvent.SetEndDateTime -> {
                _state.value = state.value.copy(
                    endDateTime = event.localDateTime
                )
            }
            is ActivityNewEvent.SetEnjoyment -> {
                _state.value = state.value.copy(enjoyment = event.enjoyment)
            }
        }
    }

}

sealed class ValidationEvent {
    object Success : ValidationEvent()
}