package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_remove.ActivityRemoveUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_off.ActivitySyncOffUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_on.ActivitySyncOnUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activity.GetActivityUseCase
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.AddActivityEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getActivityUseCase: GetActivityUseCase,
    private val activityRemoveUseCase: ActivityRemoveUseCase,
    private val activitySyncOnUseCase: ActivitySyncOnUseCase,
    private val activitySyncOffUseCase: ActivitySyncOffUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ActivityState())
    val state: StateFlow<ActivityState> = _state

    private val activityEventChannel = Channel<ActivityDetailEvent>()
    val activityEventChannelEvents = activityEventChannel.receiveAsFlow()

    init {
        onEvent(ActivityEvent.LoadActivity(false))
    }

    fun onEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.LoadActivity -> {
                viewModelScope.launch {
                    getActivity(event.refresh)
                }
            }
            is ActivityEvent.ActivityDelete -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activityRemoveUseCase(event.sportActivity).also {
                        if (it) activityEventChannel.send(ActivityDetailEvent.Deleted)
                    }
                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
            is ActivityEvent.ActivitySyncOff -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activitySyncOffUseCase(event.sportActivity)
                    getActivity(true)
                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
            is ActivityEvent.ActivitySyncOn -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activitySyncOnUseCase(event.sportActivity)
                    getActivity(true)
                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
        }
    }

    private suspend fun getActivity(fetchFromRemote: Boolean) {
        savedStateHandle.get<Int>("id")?.let { id ->
            getActivityUseCase(
                id,
                fetchFromRemote
            )?.onEach { result ->
                when (result.status) {
                    Resource.Status.SUCCESS -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            activity = result.data,
                            error = if (result.data == null) "Activity was not found." else ""
                        )
                    }
                    Resource.Status.ERROR -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.throwable?.localizedMessage ?: "Unknown error"
                        )
                    }
                    Resource.Status.LOADING -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                }
            }?.launchIn(viewModelScope)
        }
    }
}

sealed class ActivityDetailEvent {
    object Deleted : ActivityDetailEvent()
}