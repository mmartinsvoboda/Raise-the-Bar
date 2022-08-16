package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new

import androidx.lifecycle.ViewModel
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.ActivityNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
            ActivityNewEvent.AddNewEvent -> TODO()
        }
    }

}

sealed class ValidationEvent {
    object Success : ValidationEvent()
}