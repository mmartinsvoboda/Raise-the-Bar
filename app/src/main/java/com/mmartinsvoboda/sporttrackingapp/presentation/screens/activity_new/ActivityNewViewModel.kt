package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.ActivityNewUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation.*
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_on.ActivitySyncOnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ActivityNewViewModel @Inject constructor(
    private val activityNewUseCase: ActivityNewUseCase,
    private val activitySyncOnUseCase: ActivitySyncOnUseCase,
    private val descriptionValidation: DescriptionValidation,
    private val endDateTimeValidation: EndDateTimeValidation,
    private val startDateTimeValidation: StartDateTimeValidation,
    private val enjoymentValidation: EnjoymentValidation,
    private val locationValidation: LocationValidation,
    private val performanceValidation: PerformanceValidation,
    private val sportValidation: SportValidation
) : ViewModel() {

    private var _state = MutableStateFlow(ActivityNewState())
    val state: StateFlow<ActivityNewState> = _state

    private val addActivityEventChannel = Channel<AddActivityEvent>()
    val addActivityEventEvents = addActivityEventChannel.receiveAsFlow()

    fun onEvent(event: ActivityNewEvent) {
        when (event) {
            is ActivityNewEvent.AddNewEvent -> {
                addNewEvent()
            }
            is ActivityNewEvent.SetSportType -> {
                _state.value = state.value.copy(
                    sport = event.sportType, performance = ""
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
                _state.value = state.value.copy(startDateTime = event.localDateTime)
            }
            is ActivityNewEvent.SetEndDateTime -> {
                _state.value = state.value.copy(endDateTime = event.localDateTime)
            }
            is ActivityNewEvent.SetEnjoyment -> {
                _state.value = state.value.copy(enjoyment = event.enjoyment)
            }
            is ActivityNewEvent.SetSyncToServer -> {
                _state.value = state.value.copy(syncToServer = event.syncToServer)
            }
        }
    }

    private fun addNewEvent() {
        viewModelScope.launch {
            _state.value = state.value.copy(isActionInProgress = true)

            val hasError = checkForFormErrors()

            if (!hasError) {
                val newSportActivity = activityNewUseCase(
                    sportActivity = SportActivity(
                        sport = state.value.sport ?: throw IOException(),
                        description = state.value.description,
                        enjoyment = state.value.enjoyment,
                        place = state.value.location,
                        startDateTime = state.value.startDateTime ?: throw IOException(),
                        endDateTime = state.value.endDateTime ?: throw IOException(),
                        id = 0,
                        isBackedUp = false,
                        performance = state.value.performance
                    )
                )

                if (newSportActivity != null) {
                    if (state.value.syncToServer) {
                        if (activitySyncOnUseCase(newSportActivity)) {
                            addActivityEventChannel.send(AddActivityEvent.Success)
                        } else {
                            addActivityEventChannel.send(AddActivityEvent.Error("Could not sync to server."))
                        }
                    } else {
                        addActivityEventChannel.send(AddActivityEvent.Success)
                    }
                } else {
                    addActivityEventChannel.send(AddActivityEvent.Error("Could not add to local database."))
                }
            }

            _state.value = state.value.copy(isActionInProgress = false)
        }
    }

    private fun checkForFormErrors(): Boolean {
        val sportValidation = sportValidation(state.value.sport)
        val descriptionValidation = descriptionValidation(state.value.description)
        val performanceValidation = performanceValidation(state.value.performance)
        val locationValidation = locationValidation(state.value.location)
        val enjoymentValidation = enjoymentValidation(state.value.enjoyment)
        val startDateTimeValidation =
            startDateTimeValidation(state.value.startDateTime, state.value.endDateTime)
        val endDateTimeValidation =
            endDateTimeValidation(state.value.startDateTime, state.value.endDateTime)

        val hasError = listOf(
            sportValidation,
            descriptionValidation,
            performanceValidation,
            locationValidation,
            enjoymentValidation,
            startDateTimeValidation,
            endDateTimeValidation
        ).any { !it.isSuccessful }

        if (hasError) {
            _state.value = state.value.copy(
                sportError = sportValidation.error,
                descriptionError = descriptionValidation.error,
                performanceError = performanceValidation.error,
                locationError = locationValidation.error,
                enjoymentError = enjoymentValidation.error,
                startDateTimeError = startDateTimeValidation.error,
                endDateTimeError = endDateTimeValidation.error
            )
        }

        return hasError
    }

}

sealed class AddActivityEvent {
    object Success : AddActivityEvent()
    data class Error(val error: String) : AddActivityEvent()
}